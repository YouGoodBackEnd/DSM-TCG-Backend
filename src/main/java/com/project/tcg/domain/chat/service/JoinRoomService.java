package com.project.tcg.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.chat.exception.UnableJoinException;
import com.project.tcg.domain.chat.facade.RoomFacade;
import com.project.tcg.domain.chat.facade.RoomUserFacade;
import com.project.tcg.domain.chat.presentation.dto.request.ParticipateRoomRequest;
import com.project.tcg.domain.chat.presentation.dto.response.RoomNotificationResponse;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import com.project.tcg.global.socket.sercurity.ClientProperty;
import com.project.tcg.global.socket.util.SocketUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class JoinRoomService {

    private final RoomFacade roomFacade;
    private final RoomUserFacade roomUserFacade;
    private final UserFacade userFacade;
    private final SocketIOServer socketIOServer;

    @Transactional
    public void execute(SocketIOClient socketIOClient, ParticipateRoomRequest request) {

        User user = userFacade.getUserNotJoined(SocketUtil.getAccountId(socketIOClient));
        Room room = roomFacade.getRoomById(request.getRoomId());

        room.checkIsNotFulled();

        room.addRoomUser(
                RoomUser.builder()
                .room(room)
                .user(user)
                .isAccepted(false)
                .isOffered(false)
                .offer(null)
                .build()
        );

        String socketRoomId = room.getId().toString();
        socketIOClient.joinRoom(socketRoomId);

        RoomNotificationResponse roomNotificationResponse =
                new RoomNotificationResponse(socketRoomId, user.getName() + "님이 입장하셨습니다");
        socketIOServer.getRoomOperations(socketRoomId)
                .sendEvent(SocketProperty.ROOM, roomNotificationResponse);

        roomUserFacade.notifyAllOffer(room, (Object offerResponse) -> {
            socketIOServer.getRoomOperations(socketRoomId)
                    .sendEvent(SocketProperty.OFFER, offerResponse);
        });

        roomUserFacade.notifyAllAccept(room, ( Object acceptResponse) -> {
            socketIOServer.getRoomOperations(socketRoomId)
                    .sendEvent(SocketProperty.ACCEPT, acceptResponse);
        });
    }

}