package com.project.tcg.domain.user.service;


import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.domain.repository.UserRepository;
import com.project.tcg.domain.card.domain.repository.UserCardRepository;
import com.project.tcg.domain.chest.domain.repository.UserChestRepository;
import com.project.tcg.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WithdrawalService {

    private final UserRepository userRepository;

    private final UserCardRepository userCardRepository;

    private final UserChestRepository userChestRepository;

    private final UserFacade userFacade;

    @Transactional
    public void execute() {

        User user = userFacade.getCurrentUser();

        userCardRepository.deleteByUser(user);
        userChestRepository.deleteByUser(user);

        userRepository.delete(user);
    }
}