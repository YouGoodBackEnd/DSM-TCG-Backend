package com.project.tcg.domain.rank.facade;

import com.project.tcg.domain.rank.domain.Rank;
import com.project.tcg.domain.rank.domain.RankId;
import com.project.tcg.domain.rank.domain.repository.RankRepository;
import com.project.tcg.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class RankFacade {

    private final RankRepository rankRepository;

    public Long getUserRanking(User user) {

        Rank rank = getRank(user);
        return rank != null ? rank.getRanking() : null;
    }

    public Rank getRank(User user) {

        RankId rankId = RankId
                .builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now()
                        .minusHours(LocalDateTime.now().getHour())
                        .minusMinutes(LocalDateTime.now().getMinute()))
                .build();

        return rankRepository.findById(rankId).orElse(null);
    }

}