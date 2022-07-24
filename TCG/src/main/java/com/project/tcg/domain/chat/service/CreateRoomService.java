package com.project.tcg.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.chat.controller.dto.request.CreateRoomRequest;
import com.project.tcg.domain.chat.controller.dto.response.ParticipateRoomResponse;
import com.project.tcg.domain.trade.domain.Room;
import com.project.tcg.domain.trade.domain.RoomUser;
import com.project.tcg.domain.trade.domain.repository.RoomRepository;
import com.project.tcg.domain.trade.domain.repository.RoomUserRepository;
import com.project.tcg.domain.trade.facade.RoomUserFacade;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateRoomService {

    private final UserFacade userFacade;

    private final RoomUserFacade roomUserFacade;

    private final RoomRepository roomRepository;

    private final RoomUserRepository roomUserRepository;

    public void execute(SocketIOClient socketIOClient, SocketIOServer server, CreateRoomRequest request) {
        System.out.println("CreateRoomService.execute");
        User user = userFacade.getUserByClient(socketIOClient);

        System.out.println("user.getAccountId() = " + user.getAccountId());
        roomUserFacade.removeParticipatingRooms(user, socketIOClient);

        Room room = roomRepository.save(Room.builder()
                .name(request.getRoomName())
                .build());

        System.out.println("room.getName() = " + room.getName());

        RoomUser roomUser = roomUserRepository.save(RoomUser.builder()
                .room(room)
                .user(user)
                .isAccept(false)
                .build()
        );

        ParticipateRoomResponse response =  ParticipateRoomResponse
                .builder()
                .profileImage(user.getProfileImageUrl())
                .username(user.getName())
                .build();

        socketIOClient.joinRoom(room.getId().toString());
        server.getRoomOperations(room.getId().toString())
                .sendEvent(SocketProperty.ROOM, response);
    }
}