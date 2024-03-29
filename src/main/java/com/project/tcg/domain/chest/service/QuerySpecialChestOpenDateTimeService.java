package com.project.tcg.domain.chest.service;

import com.project.tcg.domain.chest.Facade.UserChestFacade;
import com.project.tcg.domain.chest.presentation.dto.response.QueryChestOpenDateTimeResponse;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class QuerySpecialChestOpenDateTimeService {

    private final UserFacade userFacade;
    private final UserChestFacade userChestFacade;

    @Transactional(readOnly = true)
    public QueryChestOpenDateTimeResponse execute() {

        User user = userFacade.getCurrentUser();

        LocalDateTime openDateTime = userChestFacade.getUserChestById(user)
                .getSpecialChestOpenDateTime();

        String formattedDateTime = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss").format(openDateTime);

        return QueryChestOpenDateTimeResponse
                .builder()
                .chestOpenDateTime(formattedDateTime)
                .isOpened(openDateTime.isBefore(LocalDateTime.now()))
                .build();
    }
}