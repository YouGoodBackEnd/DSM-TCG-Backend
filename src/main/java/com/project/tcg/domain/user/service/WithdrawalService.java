package com.project.tcg.domain.user.service;


import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.domain.repository.UserRepository;
import com.project.tcg.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WithdrawalService {

    private final UserRepository userRepository;

    private final UserFacade userFacade;

    public void execute() {

        User user = userFacade.getCurrentUser();

        userRepository.delete(user);
    }
}