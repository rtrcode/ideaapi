package com.verycool.ideaapi.service;

import com.verycool.ideaapi.model.Idea;
import com.verycool.ideaapi.model.IdeaComment;
import com.verycool.ideaapi.model.IdeaLike;
import com.verycool.ideaapi.model.User;
import com.verycool.ideaapi.repository.CommentRepository;
import com.verycool.ideaapi.repository.IdeaRepository;
import com.verycool.ideaapi.repository.LikeRepository;
import com.verycool.ideaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IdeaService {

    public static final int PAGE_SIZE = 20;
    private final IdeaRepository ideaRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    private final UserRepository userRepository;

    public List<Idea> getIdeas(int page, Sort.Direction sortDirection) {
        return ideaRepository.findAllIdeas(PageRequest
                .of(page, PAGE_SIZE,
                        Sort.by(sortDirection, "created")));
    }

    public Idea getIdea(long id) {
        return ideaRepository.findById(id)
                .orElseThrow();
    }

    public List<Idea> getIdeasWithCommentsAndLikes(int page, Sort.Direction sortDirection) {
        List<Idea> allIdeas = ideaRepository.findAllIdeas(PageRequest.of(page, PAGE_SIZE, sortDirection, "created"));

        List<Long> ids = allIdeas.stream().map(Idea::getId).collect(Collectors.toList());

        List<IdeaComment> comments = commentRepository.findAllByIdeaIdIn(ids);
        List<IdeaLike> likes = likeRepository.findAllByIdeaIdIn(ids);

        allIdeas.forEach(idea -> {
            idea.setComments(extractComments(comments, idea.getId()));
            idea.setLikes(extractLikes(likes, idea.getId()));
        });

        return allIdeas;
    }

    public Idea addIdea(Idea idea) {
        User userFromContext = userRepository.findAll().stream().findFirst().orElseThrow();
        idea.setUser(userFromContext);
        return ideaRepository.save(idea);
    }

    private List<IdeaComment> extractComments(List<IdeaComment> comments, long id) {
        return comments.stream().filter(ideaComment -> ideaComment.getIdea().getId() == id).collect(Collectors.toList());
    }

    private List<IdeaLike> extractLikes(List<IdeaLike> likes, long id) {
        return likes.stream().filter(ideaLike -> ideaLike.getIdea().getId() == id).collect(Collectors.toList());
    }
}
