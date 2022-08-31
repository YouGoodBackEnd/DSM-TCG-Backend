package com.project.tcg.domain.user.service;

import com.project.tcg.domain.auth.presentation.dto.TokenResponse;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.domain.repository.UserRepository;
import com.project.tcg.domain.user.exception.PasswordMismatchException;
import com.project.tcg.domain.user.exception.UserNotFoundException;
import com.project.tcg.domain.user.presentation.dto.request.LoginRequest;
import com.project.tcg.global.security.jwt.JwtProperties;
import com.project.tcg.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final JwtProperties jwtProperties;

    @Transactional
    public TokenResponse execute(LoginRequest request) {

        String accountId = request.getAccountId();
        String password = request.getPassword();

        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw PasswordMismatchException.EXCEPTION;

        String accessToken = jwtTokenProvider.createAccessToken(accountId);
        String refreshToken = jwtTokenProvider.createRefreshToken(accountId);

        return TokenResponse
                .builder()
                .accessToken(accessToken)
                .expiredAt(LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .build();
    }
}