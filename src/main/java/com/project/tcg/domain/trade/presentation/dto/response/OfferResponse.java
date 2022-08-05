package com.project.tcg.domain.trade.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OfferResponse {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("is_offered")
    private Boolean isOffered;

    @JsonProperty("card_id")
    private Long cardId;

    @JsonProperty("card_count")
    private Integer cardCount;

    @JsonProperty("coin")
    private Integer coin;

}