package com.project.tcg.domain.chat.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ChatResponse {

    private Long userId;

    private String username;

    private String profileImageUrl;

    private String emojiImageUrl;

    private String chat;

    private String sentAt;
}