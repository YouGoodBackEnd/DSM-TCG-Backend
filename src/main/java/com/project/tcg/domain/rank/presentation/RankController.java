package com.project.tcg.domain.rank.presentation;

import com.project.tcg.domain.rank.presentation.dto.response.QueryRankListResponse;
import com.project.tcg.domain.rank.service.QueryRankListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/ranks")
@RestController
public class RankController {

    private final QueryRankListService queryRankListService;

    @GetMapping("/{date}")
    public QueryRankListResponse queryUserRankList(@PathVariable String date){

        return queryRankListService.execute(LocalDateTime.parse(date));
    }
}