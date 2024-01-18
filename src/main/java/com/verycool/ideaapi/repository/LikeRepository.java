package com.verycool.ideaapi.repository;

import com.verycool.ideaapi.model.IdeaLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<IdeaLike, Long> {
    List<IdeaLike> findAllByIdeaIdIn(List<Long> ids);
}
