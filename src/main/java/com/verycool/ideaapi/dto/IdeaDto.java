package com.verycool.ideaapi.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class IdeaDto {

    private long id;
    private String title;
    private String content;
    private String imageUrl;
    private LocalDateTime created;
}
