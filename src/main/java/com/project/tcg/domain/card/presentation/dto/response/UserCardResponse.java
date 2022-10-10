package com.project.tcg.domain.card.presentation.dto.response;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.UserCard;
import com.project.tcg.domain.card.domain.enums.Grade;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCardResponse {

    private CardResponse card;
    private Grade grade;
    private int count;

    public static UserCardResponse of(UserCard userCard) {

        Card card = userCard.getCard();

        return UserCardResponse
                .builder()
                .card(CardResponse.of(card))
                .grade(card.getGrade())
                .count(userCard.getCount())
                .build();
    }
}