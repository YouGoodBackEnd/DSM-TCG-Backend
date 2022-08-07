package com.project.tcg.domain.chat.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRequest {

    @JsonProperty("room_id")
    private Long roomId;

    @JsonProperty("emoji_id")
    private Long emojiId;

    @JsonProperty("chat")
    private String chat;
}