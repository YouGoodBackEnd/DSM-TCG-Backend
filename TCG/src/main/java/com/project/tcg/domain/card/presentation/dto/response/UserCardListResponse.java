package com.project.tcg.domain.card.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserCardListResponse {

    private  List<CardInfoResponse> cardList;
}