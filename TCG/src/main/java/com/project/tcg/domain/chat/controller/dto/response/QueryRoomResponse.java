package com.project.tcg.domain.chat.controller.dto.response;

import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryRoomResponse {

    private Long roomId;

    private String roomName;

    private String profileImage;

    private String username;

    private int roomUserCount;

    public static QueryRoomResponse of(Room room) {

        User roomMaster = room.getRoomUsers().get(0).getUser();

        return QueryRoomResponse
                .builder()
                .roomId(room.getId())
                .roomName(room.getName())
                .profileImage(roomMaster.getProfileImageUrl())
                .username(roomMaster.getName())
                .roomUserCount(room.getRoomUsers().size())
                .build();
    }
}