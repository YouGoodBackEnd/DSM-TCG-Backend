package com.project.tcg.domain.trade.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Offer {

    private Long cardId;

    private Integer cardCount;

    private Integer coin;

}