package com.project.tcg.domain.card.facade;


import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.repository.CardRepository;
import com.project.tcg.domain.card.exception.CardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CardFacade {

    private final CardRepository cardRepository;

    public Card getCardById(Long id) {

        if (id == null) return null;
        else return cardRepository.findById(id)
                    .orElseThrow(() -> CardNotFoundException.EXCEPTION);
    }
}