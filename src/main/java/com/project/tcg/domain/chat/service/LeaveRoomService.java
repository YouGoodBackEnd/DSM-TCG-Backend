package com.project.tcg.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.chat.presentation.dto.response.RoomNotificationResponse;
import com.project.tcg.domain.chat.facade.RoomUserFacade;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LeaveRoomService {

    private final UserFacade userFacade;
    private final RoomUserFacade roomUserFacade;
    private final SocketIOServer socketIOServer;

    @Transactional
    public void execute(SocketIOClient socketIOClient) {

        User user = userFacade.getUserByClient(socketIOClient);

        roomUserFacade.removeParticipatingRooms(user);
        socketIOClient
                .getAllRooms()
                .forEach(room -> {

                    socketIOClient.leaveRoom(room);

                    RoomNotificationResponse response =
                            new RoomNotificationResponse(Long.valueOf(room), user.getName() + "님이 나가셨습니다.");

                    socketIOServer.getRoomOperations(room)
                            .sendEvent(SocketProperty.ROOM, response);
                });
    }
}