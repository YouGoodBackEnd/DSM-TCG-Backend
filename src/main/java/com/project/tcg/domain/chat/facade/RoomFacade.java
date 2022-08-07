package com.project.tcg.domain.chat.facade;

import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.repository.RoomRepository;
import com.project.tcg.domain.chat.exception.OverstaffedRoomException;
import com.project.tcg.domain.chat.exception.RoomNotFoundException;
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

    public boolean isNotEmptyRoom(Room room) {

        if (room.getRoomUsers().size() == 0) {
            roomRepository.delete(room);
            return false;
        }
        return true;
    }

    public void checkIsNotFulledRoom(Room room) {
        if (!isNotFulledRoom(room))
            throw OverstaffedRoomException.EXCEPTION;
    }

    public boolean isNotFulledRoom(Room room) {
        return 2 > room.getRoomUsers().size();
    }

}