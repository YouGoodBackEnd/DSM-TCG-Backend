package com.project.tcg.domain.card.presentation;

import com.project.tcg.domain.card.presentation.dto.request.CreateCardRequest;
import com.project.tcg.domain.card.presentation.dto.response.CardListResponse;
import com.project.tcg.domain.card.presentation.dto.response.CardResponse;
import com.project.tcg.domain.card.service.FindMyCardService;
import com.project.tcg.domain.card.service.FindUserCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CardController {

    private final CreateCardService createCardService;

    private final FindMyCardService findMyCardService;

    private final FindUserCardService findUserCardService;

    private final FindAllCardService findAllCardService;


    @PostMapping("/card")
    public CardResponse createCard(@RequestBody CreateCardRequest request){
        return createCardService.execute(request);
    }

    @GetMapping("/card/all")
    public CardResponse findAllCard(@RequestBody CreateCardRequest request){
        return createCardService.execute(request);
    }

    @GetMapping("/card")
    public CardListResponse findMyCard(){
        return findMyCardService.execute();
    }

    @GetMapping("/card/{user-id}")
    public CardListResponse findUserCard(@PathVariable("user-id") Long userId){
        return findUserCardService.execute(userId);
    }

    @GetMapping("/card/all")
    public CardListResponse findAllCard(){
        return findAllCardService.execute();
    }

}