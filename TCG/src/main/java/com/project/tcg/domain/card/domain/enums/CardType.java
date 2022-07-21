package com.project.tcg.domain.card.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardType {
    A("a");

    private final String code;
}