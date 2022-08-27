package com.project.tcg.domain.chat.domain.repository;

import com.project.tcg.domain.chat.domain.Room;

public interface CustomRoomUserRepository {

    void cancelAllOffer(Room room);
    void cancelAllAccept(Room room);
    void deleteAllByAccountId(String accountId);
}