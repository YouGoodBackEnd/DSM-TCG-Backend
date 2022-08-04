package com.project.tcg.domain.user.domain;

import com.project.tcg.domain.card.domain.enums.Grade;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class CardCount {

    @NotNull
    @Column(name = "ss_grade_card_count")
    private Integer SSGradeCardCount;

    @NotNull
    @Column(name = "s_grade_card_count")
    private Integer SGradeCardCount;

    @NotNull
    @Column(name = "a_grade_card_count")
    private Integer AGradeCardCount;

    @NotNull
    @Column(name = "b_grade_card_count")
    private Integer BGradeCardCount;

    @NotNull
    @Column(name = "c_grade_card_count")
    private Integer CGradeCardCount;

    public static CardCount init() {
        return CardCount
                .builder()
                .SSGradeCardCount(0)
                .SGradeCardCount(0)
                .AGradeCardCount(0)
                .BGradeCardCount(0)
                .CGradeCardCount(0)
                .build();
    }

    public void addCount(Grade grade) {
        switch (grade) {
            case SS:
                this.SSGradeCardCount += 1;
            case S:
                this.SGradeCardCount += 1;
            case A:
                this.AGradeCardCount += 1;
            case B:
                this.BGradeCardCount += 1;
            case C:
                this.CGradeCardCount += 1;
        }
    }
}