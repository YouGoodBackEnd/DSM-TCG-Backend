package com.project.tcg.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.chat.facade.RoomUserFacade;
import com.project.tcg.domain.chat.presentation.dto.response.RoomNotificationResponse;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import com.project.tcg.global.socket.util.SocketUtil;
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

        User user = userFacade.getUserAndFetchRoom(SocketUtil.getAccountId(socketIOClient));
        RoomUser roomUser = user.getRoomUser();
        Room room = roomUser.getRoom();

        room.removeRoomUser(roomUser);

        roomUserFacade.cancelAllAccept(room);

        roomUserFacade.notifyAllAccept(room, (Object acceptResponse) -> {
            socketIOServer.getRoomOperations(room.getId().toString())
                    .sendEvent(SocketProperty.ACCEPT, acceptResponse);
        });

        String socketRoomId = room.getId().toString();
        socketIOClient.leaveRoom(socketRoomId);

        RoomNotificationResponse response =
                new RoomNotificationResponse(socketRoomId, user.getName() + "님이 나갔습니다.");
        socketIOServer.getRoomOperations(socketRoomId)
                .sendEvent(SocketProperty.ROOM, response);
    }

}