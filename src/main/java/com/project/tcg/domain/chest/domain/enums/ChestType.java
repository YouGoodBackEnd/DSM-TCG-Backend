package com.project.tcg.domain.chest.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChestType {
    FREE_CHEST(60),
    SPECIAL_CHEST(1440);

    private final Integer renewalTime;
}