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

        User user = userFacade.getUserByClient(socketIOClient);

        Room room = roomRepository.save(Room
                .builder()
                .name(request.getRoomName())
                .build());

        roomUserFacade.checkRoomUserIsNotExist(room, user);

        RoomUser roomUser = roomUserRepository.save(RoomUser
                .builder()
                .user(user)
                .room(room)
                .isAccepted(false)
                .isOffered(false)
                .build()
        );

        socketIOClient.joinRoom(room.getId().toString());

        RoomNotificationResponse response =
                new RoomNotificationResponse(room.getId(), user.getName() + "님이 입장헀습니다");

        socketIOServer.getRoomOperations(room.getId().toString())
                .sendEvent(SocketProperty.ROOM, response);

        roomUserFacade.notifyRoomUserOfferState(room.getId(), roomUser, (String roomId, Object offerResponse) -> {
            socketIOServer.getRoomOperations(roomId)
                    .sendEvent(SocketProperty.OFFER, offerResponse);
        });

        roomUserFacade.notifyRoomUserAcceptState(room.getId(), roomUser, (String roomId, Object acceptResponse) -> {
            socketIOServer.getRoomOperations(roomId)
                    .sendEvent(SocketProperty.ACCEPT, acceptResponse);
        });
    }
}