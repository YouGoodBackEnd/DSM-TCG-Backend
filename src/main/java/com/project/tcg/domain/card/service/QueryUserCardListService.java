package com.project.tcg.domain.card.service;

import com.project.tcg.domain.card.domain.enums.CardCode;
import com.project.tcg.domain.card.domain.repository.UserCardRepository;
import com.project.tcg.domain.card.presentation.dto.response.QueryUserCardListResponse;
import com.project.tcg.domain.card.presentation.dto.response.UserCardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryUserCardListService {

    private final UserCardRepository userCardRepository;

    public QueryUserCardListResponse execute(Long userId) {

        return new QueryUserCardListResponse(
                Arrays.stream(CardCode.values())
                .map(cardCode -> userCardRepository.findByCard_CodeAndUser_Id(cardCode, userId))
                .filter(cards -> (0 < cards.size()))
                .map(UserCardResponse::of)
                .collect(Collectors.toList()));
    }
}