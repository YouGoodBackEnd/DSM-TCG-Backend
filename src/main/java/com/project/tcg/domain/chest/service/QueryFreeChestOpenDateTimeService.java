package com.project.tcg.domain.chest.service;

import com.project.tcg.domain.chest.Facade.UserChestFacade;
import com.project.tcg.domain.chest.presentation.dto.response.QueryChestOpenDateTimeResponse;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class QueryFreeChestOpenDateTimeService {

    private final UserFacade userFacade;
    private final UserChestFacade userChestFacade;

    public QueryChestOpenDateTimeResponse execute() {

        User user = userFacade.getCurrentUser();

        LocalDateTime openDateTime = userChestFacade.getUserChestById(user)
                .getFreeChestOpenDateTime();

        return QueryChestOpenDateTimeResponse
                .builder()
                .chestOpenDateTime(openDateTime)
                .isOpened(openDateTime.isAfter(LocalDateTime.now()))
                .build();
    }
}