package com.project.tcg.domain.card.presentation;

import com.project.tcg.domain.card.presentation.dto.response.QueryUserCardListResponse;
import com.project.tcg.domain.card.service.QueryMyCardListService;
import com.project.tcg.domain.card.service.QueryUserCardListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/cards")
@RestController
public class CardController {

    private final QueryMyCardListService queryMyCardListService;

    private final QueryUserCardListService queryUserCardListService;

    @GetMapping
    public QueryUserCardListResponse findMyCard(){
        return queryMyCardListService.execute();
    }

    @GetMapping("/{user-id}")
    public QueryUserCardListResponse findUserCard(@PathVariable("user-id") Long userId){
        return queryUserCardListService.execute(userId);
    }

}