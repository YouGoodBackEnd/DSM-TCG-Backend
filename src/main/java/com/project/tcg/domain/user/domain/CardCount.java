package com.project.tcg.domain.user.domain;

import com.project.tcg.domain.card.domain.enums.Grade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@Embeddable
public class CardCount {

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

    @NotNull
    @Column(name = "d_grade_card_count")
    private Integer DGradeCardCount;

    public CardCount() {
        this.SGradeCardCount = 0;
        this.AGradeCardCount = 0;
        this.BGradeCardCount = 0;
        this.CGradeCardCount = 0;
        this.DGradeCardCount = 0;
    }

    public void addCount(Grade grade, int cardCount) {
        switch (grade) {
            case S:
                this.SGradeCardCount += cardCount;
                break;
            case A:
                this.AGradeCardCount += cardCount;
                break;
            case B:
                this.BGradeCardCount += cardCount;
                break;
            case C:
                this.CGradeCardCount += cardCount;
                break;
            case D:
                this.DGradeCardCount += cardCount;
                break;
        }
    }

    public void removeCount(Grade grade, int cardCount) {
        switch (grade) {
            case S:
                this.SGradeCardCount -= cardCount;
                break;
            case A:
                this.AGradeCardCount -= cardCount;
                break;
            case B:
                this.BGradeCardCount -= cardCount;
                break;
            case C:
                this.CGradeCardCount -= cardCount;
                break;
            case D:
                this.DGradeCardCount -= cardCount;
                break;
        }
    }
}