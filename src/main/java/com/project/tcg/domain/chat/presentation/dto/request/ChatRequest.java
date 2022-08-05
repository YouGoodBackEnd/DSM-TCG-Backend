package com.project.tcg.domain.chat.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.tcg.domain.chat.domain.enums.EmojiType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRequest {

    @JsonProperty("room_id")
    private Long roomId;

    @JsonProperty("emoji_type")
    private EmojiType emojiType;

    @JsonProperty("chat")
    private String chat;
}