package com.project.tcg.domain.chest.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ChestPurchaseRequest {

    @NotNull(message = "price는 null이어선 안됩니다")
    private Integer price;
}