package com.project.tcg.domain.trade.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TradeResponse {

    @JsonProperty("notification")
    private String notification;
}