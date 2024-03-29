package com.project.tcg.domain.user.service;

import com.project.tcg.domain.rank.facade.RankFacade;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.domain.user.presentation.dto.response.QueryUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryMyInfoService {

    private final UserFacade userFacade;
    private final RankFacade rankFacade;

    @Transactional(readOnly = true)
    public QueryUserInfoResponse execute(){

        User user = userFacade.getCurrentUser();

        return QueryUserInfoResponse
                .builder()
                .userId(user.getId())
                .name(user.getName())
                .profileImageUrl(user.getProfileImageUrl())
                .coin(user.getCoin())
                .cardCount(user.getCardCount())
                .rank(rankFacade.getUserRanking(user))
                .build();
    }
}