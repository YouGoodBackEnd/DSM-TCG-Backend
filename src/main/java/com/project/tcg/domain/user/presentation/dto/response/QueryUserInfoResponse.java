package com.project.tcg.domain.user.presentation.dto.response;

import com.project.tcg.domain.user.domain.CardCount;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryUserInfoResponse {

    private final Long userId;
    private final String name;
    private final String profileImageUrl;
    private final Integer coin;
    private final CardCount cardCount;
    private final Long rank;
}