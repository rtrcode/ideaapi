package com.verycool.ideaapi.controller;

import com.verycool.ideaapi.dto.IdeaDto;
import com.verycool.ideaapi.model.Idea;

import java.util.List;
import java.util.stream.Collectors;

public class IdeaDtoMapper {

    private IdeaDtoMapper(){
    }

    public static List<IdeaDto> mapToDtos(List<Idea> ideas) {
        return ideas.stream().map(idea -> mapIdeaToDto(idea))
                .collect(Collectors.toList());
    }

    private static IdeaDto mapIdeaToDto(Idea idea) {
        return IdeaDto.builder()
                .id(idea.getId())
                .title(idea.getTitle())
                .content(idea.getContent())
                .created(idea.getCreated())
                .build();
    }
}
