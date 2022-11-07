package com.project.tcg.domain.card.presentation.dto.response;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.UserCard;
import com.project.tcg.domain.card.domain.enums.Grade;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCardResponse {

    private Long id;
    private String name;
    private String imageUrl;
    private String description;
    private Grade grade;
    private int count;

    public static UserCardResponse of(UserCard userCard) {

        Card card = userCard.getCard();

        return UserCardResponse
                .builder()
                .id(card.getId())
                .name(card.getName())
                .imageUrl(card.getCardImageUrl())
                .grade(card.getGrade())
                .description(card.getDescription())
                .count(userCard.getCount())
                .build();
    }
}