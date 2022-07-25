package com.project.tcg.domain.trade.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OfferResponse {

    private Boolean isOffered;

    private Long cardId;

    private Integer coin;

}