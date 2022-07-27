package com.project.tcg.domain.card.presentation.dto.response;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.Grade;
import com.project.tcg.domain.card.domain.UserCard;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserCardResponse {

    private Long cardId;

    private String name;

    private String cardImageUrl;

    private Grade grade;

    private int count;

    public static UserCardResponse of(List<UserCard> cards) {

        Card card = cards.get(0).getCard();

        return UserCardResponse
                .builder()
                .cardId(card.getId())
                .name(card.getName())
                .cardImageUrl(card.getCardImageUrl())
                .grade(card.getGrade())
                .count(cards.size())
                .build();
    }
}