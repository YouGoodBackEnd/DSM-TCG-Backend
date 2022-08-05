package com.project.tcg.domain.chat.facade;

import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
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

    public void removeParticipatingRooms(User user) {

        roomUserRepository
                .deleteAll(roomUserRepository.findByUser(user));
    }

    public void CheckRoomUserIsExist(Room room, User user) {

        roomUserRepository.findByRoomAndUser(room, user)
                .orElseThrow(() -> RoomNotFoundException.EXCEPTION);
    }
}