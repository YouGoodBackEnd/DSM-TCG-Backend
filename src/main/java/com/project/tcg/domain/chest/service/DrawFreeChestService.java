package com.project.tcg.domain.chest.service;

import com.project.tcg.domain.chest.Facade.ChestFacade;
import com.project.tcg.domain.chest.Facade.UserChestFacade;
import com.project.tcg.domain.chest.domain.enums.DrawProbability;
import com.project.tcg.domain.chest.presentation.dto.response.DrawChestResponse;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DrawFreeChestService {

    private final ChestFacade chestFacade;
    private final UserChestFacade userChestFacade;
    private final UserFacade userFacade;

    public DrawChestResponse execute() {

        User user = userFacade.getCurrentUser();

        userChestFacade.checkOpenableAndRenewFreeChest(user);

        return chestFacade.getDrawChestResponse(user, 3, DrawProbability.FREE_CHEST);
    }

}