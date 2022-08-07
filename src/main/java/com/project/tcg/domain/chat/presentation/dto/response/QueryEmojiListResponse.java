package com.project.tcg.domain.chat.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryEmojiListResponse {
    List<EmojiResponse> emojiList;
}