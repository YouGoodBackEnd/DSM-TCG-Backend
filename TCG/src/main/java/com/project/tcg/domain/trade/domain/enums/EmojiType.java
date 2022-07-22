package com.project.tcg.domain.trade.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmojiType {
    GOOD("EMOJI_GOOD"),
    BAD("EMOJI_BAD");

    private final String code;
}