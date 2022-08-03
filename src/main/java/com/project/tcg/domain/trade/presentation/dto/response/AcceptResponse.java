package com.project.tcg.domain.trade.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AcceptResponse {

    private Long userId;
    private Boolean isAccepted;

}