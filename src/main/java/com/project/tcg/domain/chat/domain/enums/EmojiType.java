package com.project.tcg.domain.chat.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmojiType {
    EMOJI_GOOD("EMOJI_GOOD"),
    EMOJI_BAD("EMOJI_BAD");

    private final String code;
}