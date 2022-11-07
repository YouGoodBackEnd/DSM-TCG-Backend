package com.project.tcg.domain.rank.scheduler;

import com.project.tcg.domain.rank.domain.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RankScheduler {

    private final RankRepository rankRepository;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void saveRank() {
        rankRepository.saveRank();
    }

}