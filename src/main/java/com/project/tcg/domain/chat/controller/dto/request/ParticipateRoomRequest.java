package com.project.tcg.domain.chat.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParticipateRoomRequest {

    @JsonProperty("room_id")
    Long roomId;
}