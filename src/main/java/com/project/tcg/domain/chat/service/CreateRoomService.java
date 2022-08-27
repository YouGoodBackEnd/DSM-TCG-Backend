package com.project.tcg.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.chat.facade.RoomUserFacade;
import com.project.tcg.domain.chat.presentation.dto.request.CreateRoomRequest;
import com.project.tcg.domain.chat.presentation.dto.response.RoomNotificationResponse;
import com.project.tcg.domain.chat.domain.repository.RoomRepository;
import com.project.tcg.domain.chat.domain.repository.RoomUserRepository;
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
public class CreateRoomService {

    private final UserFacade userFacade;
    private final RoomUserFacade roomUserFacade;
    private final RoomRepository roomRepository;
    private final RoomUserRepository roomUserRepository;
    private final SocketIOServer socketIOServer;

    @Transactional
    public void execute(SocketIOClient socketIOClient, CreateRoomRequest request) {

        User user = userFacade.getUserNotJoined(SocketUtil.getAccountId(socketIOClient));

        Room room = roomRepository.save(Room
                .builder()
                .name(request.getRoomName())
                .build());

        RoomUser roomUser = roomUserRepository.save(RoomUser
                .builder()
                .user(user)
                .room(room)
                .isAccepted(false)
                .isOffered(false)
                .build()
        );

        String socketRoomId = room.getId().toString();
        socketIOClient.joinRoom(socketRoomId);

        RoomNotificationResponse response =
                new RoomNotificationResponse(socketRoomId, user.getName() + "님이 입장헀습니다");
        socketIOServer.getRoomOperations(socketRoomId)
                .sendEvent(SocketProperty.ROOM, response);

        roomUserFacade.notifyOffer(roomUser, (offerResponse) -> {
            socketIOServer.getRoomOperations(socketRoomId)
                    .sendEvent(SocketProperty.OFFER, offerResponse);
        });

        roomUserFacade.notifyAccept(roomUser, (acceptResponse) -> {
            socketIOServer.getRoomOperations(socketRoomId)
                    .sendEvent(SocketProperty.ACCEPT, acceptResponse);
        });
    }
}