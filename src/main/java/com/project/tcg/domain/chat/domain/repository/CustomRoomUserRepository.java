package com.project.tcg.domain.chat.domain.repository;

import com.project.tcg.domain.chat.domain.Room;

public interface CustomRoomUserRepository {

    void cancelAllOfferIn(Room room);
    void cancelAllAccept(Room room);
    void deleteAllByAccountId(String accountId);
}