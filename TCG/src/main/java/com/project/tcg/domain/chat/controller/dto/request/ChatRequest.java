package com.project.tcg.domain.chat.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRequest {

    private String roomId;

    private String chat;
}