package com.project.tcg.domain.user.service;

import com.project.tcg.domain.auth.domain.RefreshToken;
import com.project.tcg.domain.auth.domain.repository.RefreshTokenRepository;
import com.project.tcg.domain.auth.exception.RefreshTokenNotFoundException;
import com.project.tcg.global.security.jwt.JwtProperties;
import com.project.tcg.global.security.jwt.JwtTokenProvider;
import com.project.tcg.domain.auth.presentation.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenRefreshService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtProperties jwtProperties;

    public TokenResponse execute(String refreshToken) {

        RefreshToken redisRefreshToken = refreshTokenRepository.findById(refreshToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String accountId = redisRefreshToken.getAccountId();
        String newRefreshToken = jwtTokenProvider.createRefreshToken(accountId);

        redisRefreshToken.updateToken(newRefreshToken, jwtProperties.getRefreshExp());

        String newAccessToken = jwtTokenProvider.createAccessToken(accountId);

        return TokenResponse
                .builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}