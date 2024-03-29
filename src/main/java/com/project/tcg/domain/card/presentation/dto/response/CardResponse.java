package com.project.tcg.domain.card.presentation.dto.response;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.enums.Grade;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CardResponse {

    private Long id;
    private String cardName;
    private String cardImageUrl;
    private Grade grade;
    private String description;

    public static CardResponse of(Card card) {

        return CardResponse
                .builder()
                .id(card.getId())
                .cardName(card.getName())
                .cardImageUrl(card.getCardImageUrl())
                .grade(card.getGrade())
                .description(card.getDescription())
                .build();
    }
}