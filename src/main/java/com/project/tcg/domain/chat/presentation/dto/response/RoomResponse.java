package com.project.tcg.domain.chat.presentation.dto.response;

import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomResponse {

    private Long roomId;

    private String roomName;

    private String profileImage;

    private String username;

    private int roomUserCount;

    public static RoomResponse of(Room room) {

        User roomMaster = room.getRoomUsers().get(0).getUser();

        return RoomResponse
                .builder()
                .roomId(room.getId())
                .roomName(room.getName())
                .profileImage(roomMaster.getProfileImageUrl())
                .username(roomMaster.getName())
                .roomUserCount(room.getRoomUsers().size())
                .build();
    }
}