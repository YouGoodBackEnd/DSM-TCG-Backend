package com.project.tcg.global.utils.token.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
public class TokenResponse {

    private final String accessToken;

    private final ZonedDateTime expiredAt;

    private final String refreshToken;

}