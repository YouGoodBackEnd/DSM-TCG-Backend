package com.project.tcg.domain.rank.facade;

import com.project.tcg.domain.rank.domain.UserRank;
import com.project.tcg.domain.rank.domain.UserRankId;
import com.project.tcg.domain.rank.domain.repository.UserRankRepository;
import com.project.tcg.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserRankFacade {

    private final UserRankRepository userRankRepository;

    public Long getUserRanking(User user) {

        UserRank userRank = getUserRank(user);
        return userRank != null ? userRank.getRanking() : null;
    }

    public UserRank getUserRank(User user) {
        return userRankRepository.findById(
                UserRankId.builder().build()
        ).orElse(null);
    }

}