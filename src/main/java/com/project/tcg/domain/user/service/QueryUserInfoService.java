package com.project.tcg.domain.user.service;

import com.project.tcg.domain.rank.facade.RankFacade;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.domain.user.presentation.dto.response.QueryUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryUserInfoService {

    private final UserFacade userFacade;
    private final RankFacade rankFacade;

    public QueryUserInfoResponse execute(Long userId) {

        User user = userFacade.getUserById(userId);

        return QueryUserInfoResponse
                .builder()
                .userId(user.getId())
                .name(user.getName())
                .profileImageUrl(user.getProfileImageUrl())
                .cardCount(user.getCardCount())
                .rank(rankFacade.getUserRanking(user))
                .build();
    }
}