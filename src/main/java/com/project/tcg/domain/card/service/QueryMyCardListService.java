package com.project.tcg.domain.card.service;

import com.project.tcg.domain.card.domain.enums.CardCode;
import com.project.tcg.domain.card.domain.repository.UserCardRepository;
import com.project.tcg.domain.card.presentation.dto.response.QueryUserCardListResponse;
import com.project.tcg.domain.card.presentation.dto.response.UserCardResponse;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryMyCardListService {

    private final UserCardRepository userCardRepository;

    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryUserCardListResponse execute() {

        User user = userFacade.getCurrentUser();
        Long userId = user.getId();

        List<UserCardResponse> cardList =
                Arrays.stream(CardCode.values())
                        .map(cardCode -> userCardRepository.findByCard_CodeAndUser_Id(cardCode, userId))
                        .filter(cards -> (0 < cards.size()))
                        .map(UserCardResponse::of)
                        .collect(Collectors.toList());

        return new QueryUserCardListResponse(user.getCardCount(), cardList);
    }
}