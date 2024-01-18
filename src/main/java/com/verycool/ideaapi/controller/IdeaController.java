package com.verycool.ideaapi.controller;

import com.verycool.ideaapi.dto.IdeaDto;
import com.verycool.ideaapi.model.Idea;
import com.verycool.ideaapi.service.IdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class IdeaController {

    private final IdeaService ideaService;

    @GetMapping("/ideas")
    public List<IdeaDto> getIdeas(@RequestParam(required = false) Integer page, Sort.Direction direction) {
        int pageNumber = page != null && page >= 0 ? page : 1;
        Sort.Direction sortDirection = direction != null ? direction : Sort.Direction.ASC;
        return IdeaDtoMapper.mapToDtos(ideaService.getIdeas(pageNumber - 1, sortDirection));
    }

    @GetMapping("/ideas/details")
    public List<Idea> getIdeasWithCommentsAndLikes(@RequestParam(required = false) Integer page, Sort.Direction direction) {
        int pageNumber = page !=null && page >= 0 ? page : 1;
        Sort.Direction sortDirection = direction != null ? direction : Sort.Direction.ASC;
        return ideaService.getIdeasWithCommentsAndLikes(pageNumber - 1, sortDirection);
    }

    @GetMapping("/ideas/{id}")
    public Idea getIdea(@PathVariable long id) {
        return ideaService.getIdea(id);
    }

    @PostMapping("/ideas")
    public Idea addIdea(@RequestBody Idea idea) {
        return ideaService.addIdea(idea);
    }
}
