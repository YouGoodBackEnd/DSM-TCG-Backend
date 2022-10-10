package com.project.tcg.domain.card.presentation.dto.response;

import com.project.tcg.domain.user.domain.CardCount;
import lombok.Getter;

import java.util.List;

@Getter
public class QueryUserCardListResponse {

    private CardCount cardCount;
    private Integer totalCount;
    private List<UserCardResponse> cardList;

    public QueryUserCardListResponse(CardCount cardCount, List<UserCardResponse> cardList) {
        this.cardCount = cardCount;
        this.cardList = cardList;
        this.totalCount = cardList.size();
    }
}