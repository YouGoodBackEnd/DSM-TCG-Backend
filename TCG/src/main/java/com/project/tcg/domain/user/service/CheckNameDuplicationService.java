package com.project.tcg.domain.user.service;

import com.project.tcg.domain.user.domain.repository.UserRepository;
import com.project.tcg.domain.user.exception.UserAlreadyExistException;
import com.project.tcg.domain.user.presentation.dto.request.CheckNameDuplicationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckNameDuplicationService {

    private final UserRepository userRepository;

    public void execute(CheckNameDuplicationRequest request) {

        if(userRepository.findByName(request.getName()).isPresent())
            throw UserAlreadyExistException.EXCEPTION;
    }
}