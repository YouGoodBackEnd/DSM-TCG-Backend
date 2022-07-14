package com.project.tcg.domain.user.service;

import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.domain.repository.UserRepository;
import com.project.tcg.domain.user.exception.PasswordNotValidException;
import com.project.tcg.domain.user.exception.UserNotFoundException;
import com.project.tcg.domain.user.presentation.dto.request.LoginRequest;
import com.project.tcg.global.security.jwt.JwtTokenProvider;
import com.project.tcg.global.utils.token.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    public TokenResponse execute(LoginRequest request) {

        String accountId = request.getAccountId();
        String password = request.getPassword();

        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw PasswordNotValidException.EXCEPTION;

        return jwtTokenProvider.createTokens(accountId);
    }
}