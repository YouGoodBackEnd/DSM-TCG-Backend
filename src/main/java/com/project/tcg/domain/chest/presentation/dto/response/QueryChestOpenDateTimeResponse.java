package com.project.tcg.domain.chest.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class QueryChestOpenDateTimeResponse {

    LocalDateTime chestOpenDateTime;
    Boolean isOpened;
}