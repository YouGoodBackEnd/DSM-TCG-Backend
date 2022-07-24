package com.project.tcg.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.trade.domain.Room;
import com.project.tcg.domain.trade.domain.RoomUser;
import com.project.tcg.domain.trade.domain.repository.RoomUserRepository;
import com.project.tcg.domain.trade.exception.OverstaffedRoomException;
import com.project.tcg.domain.trade.facade.RoomFacade;
import com.project.tcg.domain.chat.controller.dto.request.ParticipateRoomRequest;
import com.project.tcg.domain.chat.controller.dto.response.ParticipateRoomResponse;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ParticipateRoomService {

    private final RoomUserRepository roomUserRepository;

    private final RoomFacade roomFacade;

    private final UserFacade userFacade;

    @Transactional
    public void execute(SocketIOClient socketIOClient, SocketIOServer server, ParticipateRoomRequest request) {

        Room room = roomFacade.getRoomById(request.getRoomId());
        User user = userFacade.getUserByClient(socketIOClient);

        removeParticipatingRooms(socketIOClient);

        if (2 <= room.getRoomUsers().size())
            throw OverstaffedRoomException.EXCEPTION;

        RoomUser roomUser = roomUserRepository.save(RoomUser.builder()
                .room(room)
                .user(user)
                .isAccept(false)
                .build()
        );

        roomFacade.roomUsersAcceptFalse(room);

        ParticipateRoomResponse response = ParticipateRoomResponse
                .builder()
                .profileImage(user.getProfileImageUrl())
                .username(user.getName())
                .build();

        socketIOClient.joinRoom(room.getId().toString());
        server.getRoomOperations(room.getId().toString())
                .sendEvent(SocketProperty.ROOM, response);
    }

    private void removeParticipatingRooms(SocketIOClient socketIOClient) {
        socketIOClient.getAllRooms().forEach(socketIOClient::leaveRoom);
    }
}