package com.verycool.ideaapi.repository;

import com.verycool.ideaapi.model.IdeaComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<IdeaComment, Long> {
    List<IdeaComment> findAllByIdeaIdIn(List<Long> ids);
}
