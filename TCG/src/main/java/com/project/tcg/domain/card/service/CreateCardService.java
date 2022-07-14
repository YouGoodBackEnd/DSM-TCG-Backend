package com.project.tcg.domain.card.service;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.repository.CardRepository;
import com.project.tcg.domain.card.presentation.dto.request.CreateCardRequest;
import com.project.tcg.domain.card.presentation.dto.response.CardResponse;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCardService {

    private final CardRepository cardRepository;

    private final UserFacade userFacade;

    public CardResponse execute(CreateCardRequest request) {

        User user = userFacade.getCurrentUser();

        Card card = cardRepository.save(Card.builder()
                .name(request.getName())
                .grade(request.getGrade())
                .description(request.getDescription())
                .cardImageUrl(request.getCardImageUrl())
                .build()
        );

        return new CardResponse(card.getId());
    }
}