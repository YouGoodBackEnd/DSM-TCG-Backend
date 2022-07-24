package com.project.tcg.domain.chat.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ChatResponse {

    private String username;

    private String EmojiImageUrl;

    private String chat;

    private LocalDateTime sentAt;
}