package com.project.tcg.domain.trade.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OfferRequest {

    @JsonProperty("room_id")
    private Long roomId;

    @JsonProperty("card_id")
    private Long cardId;

    @JsonProperty("card_count")
    private Integer cardCount;

    private Integer coin;
}