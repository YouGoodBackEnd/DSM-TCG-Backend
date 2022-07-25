package com.project.tcg.domain.chat.controller.dto.request;

import com.project.tcg.domain.chat.domain.enums.EmojiType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRequest {

    private Long roomId;

    private EmojiType emojiType;

    private String chat;
}