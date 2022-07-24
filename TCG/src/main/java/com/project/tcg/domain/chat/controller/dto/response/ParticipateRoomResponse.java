package com.project.tcg.domain.chat.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ParticipateRoomResponse {

    private final Long roomId;
    private final String title;
    private final String profileImage;
    private final String username;

}