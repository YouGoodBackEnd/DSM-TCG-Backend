package com.project.tcg.domain.user.service;

import com.project.tcg.domain.auth.presentation.dto.TokenResponse;
import com.project.tcg.domain.chest.domain.UserChest;
import com.project.tcg.domain.chest.domain.repository.UserChestRepository;
import com.project.tcg.domain.user.domain.Authority;
import com.project.tcg.domain.user.domain.CardCount;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.domain.repository.UserRepository;
import com.project.tcg.domain.user.exception.UserAlreadyExistException;
import com.project.tcg.domain.user.presentation.dto.request.SignupRequest;
import com.project.tcg.global.security.jwt.JwtProperties;
import com.project.tcg.global.security.jwt.JwtTokenProvider;
import com.project.tcg.infrastructure.image.DefaultImage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;

    private final UserChestRepository userChestRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final JwtProperties jwtProperties;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenResponse execute(SignupRequest request) {

        String accountId = request.getAccountId();
        String name = request.getName();
        String password = request.getPassword();

        if (userRepository.findByAccountId(accountId).isPresent()){
            throw UserAlreadyExistException.EXCEPTION;
        }

        User user = userRepository.save(User.builder()
                .accountId(accountId)
                .name(name)
                .password(passwordEncoder.encode(password))
                .authority(Authority.USER)
                .profileImageUrl(DefaultImage.USER_PROFILE_IMAGE)
                .coin(5000)
                .diamond(0)
                .cardCount(new CardCount())
                .build()
        );

        userChestRepository.save(UserChest
                .builder()
                .user(user)
                .freeChestOpenDateTime(LocalDateTime.now().minusSeconds(1))
                .specialChestOpenDateTime(LocalDateTime.now().minusSeconds(1))
                .build());

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