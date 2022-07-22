package com.project.tcg.domain.trade.controller.dto.response;

import com.project.tcg.domain.trade.domain.RoomUser;
import com.project.tcg.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ParticipateRoomResponse {

    private final Long roomId;
    private final String title;
    private final String profileImage;
    private final String username;

    public static ParticipateRoomResponse of(RoomUser roomUser){

        User user = roomUser.getUser();

        return ParticipateRoomResponse
                .builder()
                .profileImage(user.getProfileImageUrl())
                .username(user.getName())
                .build();
    }

}