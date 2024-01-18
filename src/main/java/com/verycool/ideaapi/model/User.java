package com.verycool.ideaapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="idea_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private LocalDateTime created;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference(value="user-idea")
    private List<Idea> ideas;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value="user-like")
    private List<IdeaLike> likes;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value="user-comment")
    private List<IdeaComment> comments;

    //TODO other fields
}
