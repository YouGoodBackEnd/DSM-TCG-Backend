package com.project.tcg.domain.trade.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AcceptResponse {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("is_accepted")
    private Boolean isAccepted;

}