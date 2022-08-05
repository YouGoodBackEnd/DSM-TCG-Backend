package com.project.tcg.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.chat.presentation.dto.request.CreateRoomRequest;
import com.project.tcg.domain.chat.presentation.dto.response.RoomNotificationResponse;
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
    private final SocketIOServer socketIOServer;

    public void execute(SocketIOClient socketIOClient, CreateRoomRequest request) {

        User user = userFacade.getUserByClient(socketIOClient);

        roomUserFacade.removeParticipatingRooms(user);
        socketIOClient
                .getAllRooms()
                .forEach(socketIOClient::leaveRoom);

        Room room = roomRepository.save(Room
                .builder()
                .name(request.getRoomName())
                .build());

        roomUserRepository.save(RoomUser
                .builder()
                .user(user)
                .room(room)
                .isAccepted(false)
                .isOffered(false)
                .build()
        );

        RoomNotificationResponse response =
                new RoomNotificationResponse(room.getId(), user.getName() + "님이 입장하셨습니다");

        socketIOClient.joinRoom(room.getId().toString());
        socketIOServer.getRoomOperations(room.getId().toString())
                .sendEvent(SocketProperty.ROOM, response);
    }
}