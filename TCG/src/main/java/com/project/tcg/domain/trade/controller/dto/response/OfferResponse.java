package com.project.tcg.domain.trade.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OfferResponse {

    private Long userId;

    private Boolean isOffered;

    private Long cardId;

    private Integer cardCount;

    private Integer coin;

}