package com.project.tcg.domain.card.facade;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.CardCollection;
import com.project.tcg.domain.card.domain.repository.CardCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CardCollectionFacade {

    private final CardCollectionRepository cardCollectionRepository;

    public List<Card> getUserCardList(Long userId) {
        return cardCollectionRepository.findByUserId(userId)
                .stream()
                .map(CardCollection::getCard)
                .collect(Collectors.toList());
    }
}