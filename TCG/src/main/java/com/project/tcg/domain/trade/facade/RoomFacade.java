package com.project.tcg.domain.trade.facade;

import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.trade.domain.repository.RoomRepository;
import com.project.tcg.domain.trade.exception.RoomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RoomFacade {

    private final RoomRepository roomRepository;

    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> RoomNotFoundException.EXCEPTION);
    }

    public void roomUsersAcceptFalse(Room room) {
        room.getRoomUsers().forEach(RoomUser::acceptFalse);
    }

    public boolean isNotEmptyRoom(Room room) {

        if (room.getRoomUsers().size() == 0) {
            roomRepository.delete(room);
            return false;
        }
        return true;
    }

    public boolean isNotOverstaffedRoom(Room room) {
        return 2 > room.getRoomUsers().size();
    }
}