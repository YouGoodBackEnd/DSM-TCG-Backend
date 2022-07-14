package com.project.tcg.domain.card.presentation.dto.response;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.Grade;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CardInfoResponse {

    private Long id;

    private String name;

    private String cardImageUrl;

    private Grade grade;

    private String description;

    public static CardInfoResponse of(Card card) {
        return CardInfoResponse
                .builder()
                .id(card.getId())
                .name(card.getName())
                .cardImageUrl(card.getCardImageUrl())
                .grade(card.getGrade())
                .description(card.getDescription())
                .build();
    }
}