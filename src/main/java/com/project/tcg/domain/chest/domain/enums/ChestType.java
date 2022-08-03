package com.project.tcg.domain.chest.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChestType {
    FREE_CHEST(360),
    SPECIAL_CHEST(8640);

    private final Integer renewalTime;
}