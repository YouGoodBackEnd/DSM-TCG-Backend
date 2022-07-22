package com.project.tcg.domain.trade.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
@Builder
public class ChatResponse {

    private String username;

    @Nullable
    private String EmojiImageUrl;

    private String chat;
}