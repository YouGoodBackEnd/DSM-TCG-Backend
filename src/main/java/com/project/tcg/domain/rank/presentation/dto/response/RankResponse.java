package com.project.tcg.domain.rank.presentation.dto.response;

import com.project.tcg.domain.rank.domain.Rank;
import com.project.tcg.domain.user.domain.CardCount;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RankResponse {

    private Long userId;
    private String accountId;
    private String name;
    private String profileImageUrl;
    private CardCount cardCount;
    private Long ranking;

    public static RankResponse of(Rank rank) {
        return RankResponse
                .builder()
                .userId(rank.getUserId())
                .accountId(rank.getAccountId())
                .name(rank.getName())
                .profileImageUrl(rank.getProfileImageUrl())
                .cardCount(rank.getCardCount())
                .ranking(rank.getRanking())
                .build();
    }
}