package com.project.tcg.global.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.project.tcg.domain.chat.controller.dto.response.RoomNotificationResponse;
import com.project.tcg.domain.trade.facade.RoomUserFacade;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WebSocketDisConnectListener implements DisconnectListener {

    private final UserFacade userFacade;

    private final RoomUserFacade roomUserFacade;

    private final SocketIOServer socketIOServer;

    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {

        User user = userFacade.getUserByClient(socketIOClient);

        roomUserFacade.removeParticipatingRooms(user);

        socketIOClient
                .getAllRooms()
                .forEach(room -> {

                    RoomNotificationResponse response =
                            new RoomNotificationResponse(Long.valueOf(room), user.getName() + "님이 나가셨습니다.");

                    socketIOServer.getRoomOperations(room)
                            .sendEvent(SocketProperty.ROOM, response);

                    socketIOClient.leaveRoom(room);
                });
    }
}