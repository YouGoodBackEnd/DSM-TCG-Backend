package com.project.tcg.domain.card.service;

import com.project.tcg.domain.card.domain.repository.CardRepository;
import com.project.tcg.domain.card.facade.UserCardFacade;
import com.project.tcg.domain.card.presentation.dto.response.CardInfoResponse;
import com.project.tcg.domain.card.presentation.dto.response.CardListResponse;
import com.project.tcg.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryUserCardListService {

    private final CardRepository cardRepository;

    private final UserCardFacade userCardFacade;

    private final UserFacade userFacade;

    public CardListResponse execute(Long userId) {

        List<CardInfoResponse> cardList = userCardFacade.getUserCardList(userId)
                .stream()
                .map(CardInfoResponse::of)
                .collect(Collectors.toList());

        return new CardListResponse(cardList);
    }
}