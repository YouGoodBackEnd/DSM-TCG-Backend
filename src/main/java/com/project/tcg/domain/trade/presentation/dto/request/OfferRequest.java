package com.project.tcg.domain.trade.presentation.dto.request;

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

    @JsonProperty("coin")
    private Integer coin;
}