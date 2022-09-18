package com.project.tcg.domain.card.presentation.dto.response;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.UserCard;
import com.project.tcg.domain.card.domain.enums.Grade;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCardResponse {

    private Long cardId;
    private String name;
    private String cardImageUrl;
    private Grade grade;
    private int count;

    public static UserCardResponse of(UserCard userCard) {

        Card card = userCard.getCard();

        return UserCardResponse
                .builder()
                .cardId(card.getId())
                .name(card.getName())
                .cardImageUrl(card.getCardImageUrl())
                .grade(card.getGrade())
                .count(userCard.getCount())
                .build();
    }
}