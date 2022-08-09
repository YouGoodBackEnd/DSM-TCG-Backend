package com.project.tcg.domain.chat.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomNotificationResponse {

    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("notification")
    private String notification;

}