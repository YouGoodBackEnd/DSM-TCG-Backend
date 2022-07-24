package com.project.tcg.domain.trade.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SuggestRequest {

    private Long roomId;

    private Long cardId;

    private Integer coin;
}