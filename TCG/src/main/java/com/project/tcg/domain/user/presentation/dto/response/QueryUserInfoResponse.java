package com.project.tcg.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryUserInfoResponse {
    private final Long userId;
    private final String name;
    private final String profileImageUrl;
    private final Long rank;
}