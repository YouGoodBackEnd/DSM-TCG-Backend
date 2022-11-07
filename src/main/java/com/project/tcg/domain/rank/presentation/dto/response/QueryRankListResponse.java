package com.project.tcg.domain.rank.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryRankListResponse {

    private List<RankResponse> rankList;
}