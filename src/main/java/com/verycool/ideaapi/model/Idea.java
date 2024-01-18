package com.verycool.ideaapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @JsonBackReference(value="user-idea")
    private User user;

    private String title;

    private String content;

    @Column(name="image_url")
    private String imageUrl;

    private LocalDateTime created;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idea")
    @JsonManagedReference(value="idea-comment")
    private List<IdeaComment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idea")
    @JsonManagedReference(value="idea-like")
    private List<IdeaLike> likes;
}
