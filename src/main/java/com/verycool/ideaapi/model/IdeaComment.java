package com.verycool.ideaapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="idea_comment")
@Getter
@Setter
public class IdeaComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="idea_id", nullable = false)
    @JsonBackReference(value="idea-comment")
    private Idea idea;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @JsonBackReference(value="user-comment")
    private User user;

    private String content;

    private LocalDateTime created;

}
