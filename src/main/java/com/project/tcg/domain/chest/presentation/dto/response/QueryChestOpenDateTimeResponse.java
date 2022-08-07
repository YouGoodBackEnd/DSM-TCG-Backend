package com.project.tcg.domain.chest.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryChestOpenDateTimeResponse {

    String chestOpenDateTime;
    Boolean isOpened;
}