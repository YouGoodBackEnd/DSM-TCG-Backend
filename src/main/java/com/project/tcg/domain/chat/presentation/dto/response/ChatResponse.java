package com.project.tcg.domain.chat.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ChatResponse {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("profile_image_url")
    private String profileImageUrl;

    @JsonProperty("emoji_image_url")
    private String emojiImageUrl;

    @JsonProperty("chat")
    private String chat;

    @JsonProperty("sent_at")
    private String sentAt;
}