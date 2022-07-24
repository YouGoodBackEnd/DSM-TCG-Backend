package com.project.tcg.domain.trade.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SuggestResponse {

    private Long cardId;

    private Integer coin;

}