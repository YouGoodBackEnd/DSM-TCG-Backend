package com.project.tcg.domain.card.facade;

import com.project.tcg.domain.card.domain.repository.UserCardRepository;
import com.project.tcg.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserCardFacade {

    private final UserCardRepository userCardRepository;

    public int getUserCardCount(Long cardId, User user) {
        return userCardRepository.findByCard_IdAndUser(cardId, user).size();
    }
}