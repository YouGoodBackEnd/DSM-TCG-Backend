package com.project.tcg.domain.rank.presentation;

import com.project.tcg.domain.rank.presentation.dto.response.QueryRankListResponse;
import com.project.tcg.domain.rank.service.QueryRankListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/ranks")
@RestController
public class RankController {

    private final QueryRankListService queryRankListService;

    @GetMapping("")
    public QueryRankListResponse queryUserRankList(){
        return queryRankListService.execute();
    }
}