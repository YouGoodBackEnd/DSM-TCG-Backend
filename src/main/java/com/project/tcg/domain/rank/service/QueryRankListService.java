package com.project.tcg.domain.rank.service;

import com.project.tcg.domain.rank.domain.repository.RankRepository;
import com.project.tcg.domain.rank.presentation.dto.response.QueryRankListResponse;
import com.project.tcg.domain.rank.presentation.dto.response.RankResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryRankListService {

    private final RankRepository rankRepository;

    @Transactional(readOnly = true)
    public QueryRankListResponse execute() {

        List<RankResponse> lankList = rankRepository.findTop100ByCreatedAtOrderByRanking(LocalDateTime.now().toLocalDate())
                .stream()
                .map(RankResponse::of)
                .collect(Collectors.toList());

        return new QueryRankListResponse(lankList);
    }
}