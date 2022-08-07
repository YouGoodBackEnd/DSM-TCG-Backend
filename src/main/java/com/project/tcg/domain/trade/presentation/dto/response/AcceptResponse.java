package com.project.tcg.domain.trade.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AcceptResponse {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("is_accepted")
    private Boolean isAccepted;

}