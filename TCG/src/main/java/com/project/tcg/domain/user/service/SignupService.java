package com.project.tcg.domain.user.service;

import com.project.tcg.domain.user.domain.Authority;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.domain.repository.UserRepository;
import com.project.tcg.domain.user.exception.UserAlreadyExistException;
import com.project.tcg.domain.user.presentation.dto.request.SignupRequest;
import com.project.tcg.global.security.jwt.JwtProperties;
import com.project.tcg.global.security.jwt.JwtTokenProvider;
import com.project.tcg.global.utils.token.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtProperties jwtProperties;

    @Transactional
    public TokenResponse execute(SignupRequest request) {

        String accountId = request.getAccountId();
        String name = request.getName();
        String password = request.getPassword();

        if (userRepository.findByAccountId(accountId).isPresent())
            throw UserAlreadyExistException.EXCEPTION;

        User user = userRepository.save(User.builder()
                .accountId(accountId)
                .name(name)
                .password(password)
                .authority(Authority.USER)
                .gold(5000)
                .diamond(0)
                .build());

        String accessToken = jwtTokenProvider.createAccessToken(request.getAccountId());
        String refreshToken = jwtTokenProvider.createRefreshToken(request.getAccountId());

        return TokenResponse
                .builder()
                .accessToken(accessToken)
                .expiredAt(ZonedDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .build();
    }
}