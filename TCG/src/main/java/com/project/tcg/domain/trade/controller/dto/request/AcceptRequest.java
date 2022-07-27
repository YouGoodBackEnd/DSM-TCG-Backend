package com.project.tcg.domain.trade.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AcceptRequest {

    @JsonProperty("room_id")
    private Long roomId;

}