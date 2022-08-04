package com.project.tcg.domain.chat.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomNotificationResponse {

    private Long roomId;

    private String notification;

}