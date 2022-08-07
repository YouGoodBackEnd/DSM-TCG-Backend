package com.project.tcg.domain.chest.presentation.dto.response;

import com.project.tcg.domain.card.presentation.dto.response.CardResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DrawChestResponse {

    private List<CardResponse> cardList;
    private int coin;
    private int diamond;
}