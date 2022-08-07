package com.project.tcg.domain.card.presentation.dto.response;

import com.project.tcg.domain.user.domain.CardCount;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryUserCardListResponse {

    private CardCount cardCount;
    private List<UserCardResponse> cardList;
}