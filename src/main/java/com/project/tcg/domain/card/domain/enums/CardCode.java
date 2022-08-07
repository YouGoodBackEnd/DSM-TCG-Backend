package com.project.tcg.domain.card.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardCode {

    SS_GRADE_CARD("SSGradeCard"),
    S_GRADE_CARD("SGradeCard"),
    A_GRADE_CARD("AGradeCard"),
    B_GRADE_CARD("BGradeCard"),
    C_GRADE_CARD("CGradeCard");

    private final String code;
}