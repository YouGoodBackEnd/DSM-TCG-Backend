package com.project.tcg.domain.user.service;

import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.domain.repository.UserRepository;
import com.project.tcg.domain.user.exception.PasswordMismatchException;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.domain.user.presentation.dto.request.UpdatePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePasswordService {

    private final UserFacade userFacade;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(UpdatePasswordRequest request) {

        User user = userFacade.getCurrentUser();

        if(!passwordEncoder.matches(request.getOldPassword(),user.getPassword()))
            throw PasswordMismatchException.EXCEPTION;

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
    }
}