package com.project.tcg.domain.chest.service;

import com.project.tcg.domain.chest.Facade.ChestFacade;
import com.project.tcg.domain.chest.domain.enums.DrawProbability;
import com.project.tcg.domain.chest.presentation.dto.request.ChestPurchaseRequest;
import com.project.tcg.domain.chest.presentation.dto.response.DrawChestResponse;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DrawSilverChestService {

    private final ChestFacade chestFacade;
    private final UserFacade userFacade;

    @Transactional
    public DrawChestResponse execute(ChestPurchaseRequest request) {

        User user = userFacade.getCurrentUser();

        user.removeCoin(request.getPrice());

        return chestFacade.getDrawChestResponse(user, 3, DrawProbability.SILVER_CHEST);
    }
}