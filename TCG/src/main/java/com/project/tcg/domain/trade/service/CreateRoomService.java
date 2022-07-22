package com.project.tcg.domain.trade.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.trade.controller.dto.request.CreateRoomRequest;
import com.project.tcg.domain.trade.controller.dto.response.ParticipateRoomResponse;
import com.project.tcg.domain.trade.domain.Room;
import com.project.tcg.domain.trade.domain.RoomUser;
import com.project.tcg.domain.trade.domain.repository.RoomRepository;
import com.project.tcg.domain.trade.domain.repository.RoomUserRepository;
import com.project.tcg.domain.trade.facade.RoomUserFacade;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.websocket.SocketProperty;
import com.project.tcg.global.websocket.sercurity.ClientProperty;
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

        User user = userFacade.getUserByAccountId(socketIOClient.get(ClientProperty.USER_KEY));

        roomUserFacade.removeParticipatingRooms(user, socketIOClient);

        Room room = roomRepository.save(Room.builder()
                .name(request.getRoomName())
                .build());

        RoomUser roomUser = roomUserRepository.save(RoomUser.builder()
                .room(room)
                .user(user)
                .isAccept(false)
                .build()
        );

        socketIOClient.joinRoom(room.getId().toString());
        socketIOClient.sendEvent(SocketProperty.ROOM, ParticipateRoomResponse.of(roomUser));
    }
}