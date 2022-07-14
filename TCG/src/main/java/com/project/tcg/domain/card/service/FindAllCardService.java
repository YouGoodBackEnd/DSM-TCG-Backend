package com.project.tcg.domain.card.service;

import com.project.tcg.domain.card.domain.repository.CardRepository;
import com.project.tcg.domain.card.presentation.dto.response.CardInfoResponse;
import com.project.tcg.domain.card.presentation.dto.response.CardListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FindAllCardService {

    private final CardRepository cardRepository;

    public CardListResponse execute() {

        List<CardInfoResponse> cardList = cardRepository.findBy()
                .stream()
                .map(CardInfoResponse::of)
                .collect(Collectors.toList());

        return new CardListResponse(cardList);
    }
}