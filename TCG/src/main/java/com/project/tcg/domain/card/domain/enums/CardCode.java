package com.project.tcg.domain.card.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardCode {
    A("a");

    private final String code;
}