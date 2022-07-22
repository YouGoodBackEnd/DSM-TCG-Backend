package com.project.tcg.domain.trade.facade;

import com.corundumstudio.socketio.SocketIOClient;
import com.project.tcg.domain.trade.domain.Room;
import com.project.tcg.domain.trade.domain.RoomUser;
import com.project.tcg.domain.trade.domain.repository.RoomUserRepository;
import com.project.tcg.domain.trade.exception.RoomNotFoundException;
import com.project.tcg.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RoomUserFacade {

    private final RoomUserRepository roomUserRepository;

    public RoomUser getRoomUserByRoomAndUser(Room room, User user) {
        return roomUserRepository.findByRoomAndUser(room, user)
                .orElseThrow(() -> RoomNotFoundException.EXCEPTION);
    }

    public void removeParticipatingRooms(User user, SocketIOClient socketIOClient) {
        roomUserRepository.findByUser(user).ifPresent(roomUserRepository::delete);
        socketIOClient.getAllRooms().forEach(socketIOClient::leaveRoom);
    }
}