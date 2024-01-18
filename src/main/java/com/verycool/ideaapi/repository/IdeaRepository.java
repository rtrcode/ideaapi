package com.verycool.ideaapi.repository;

import com.verycool.ideaapi.model.Idea;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
    @Query(value = "SELECT i FROM Idea i")
    List<Idea> findAllIdeas(Pageable page);
}
