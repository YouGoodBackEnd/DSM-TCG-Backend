package com.project.tcg.domain.card.facade;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.CardCollection;
import com.project.tcg.domain.card.domain.repository.CardCollectionRepository;
import com.project.tcg.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CardCollectionFacade {

    private final CardCollectionRepository cardCollectionRepository;

    public Card getCardById(Long cardId, User user) {
        return cardCollectionRepository.findByCardIdAndUser(cardId, user)
                .map(CardCollection::getBadge)
                .orElseThrow(() -> BadgeNotFoundException.EXCEPTION);
    }
}