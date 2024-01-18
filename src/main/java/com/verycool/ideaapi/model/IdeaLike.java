package com.verycool.ideaapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "idea_like")
@Getter
@Setter
public class IdeaLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @JsonBackReference(value="user-like")
    private User user;

    @ManyToOne
    @JoinColumn(name="idea_id", nullable = false)
    @JsonBackReference(value="idea-like")
    private Idea idea;

    private LocalDateTime created;
}
