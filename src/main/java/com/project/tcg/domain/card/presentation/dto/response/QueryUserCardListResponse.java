package com.project.tcg.domain.card.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryUserCardListResponse {

    private  List<UserCardResponse> cardList;
}