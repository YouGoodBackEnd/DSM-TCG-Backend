package com.project.tcg.domain.user.domain.repository;

import com.project.tcg.domain.chat.domain.repository.vo.UserRoomVO;
import com.project.tcg.domain.user.domain.User;

import java.util.Optional;

public interface CustomUserRepository {
    Optional<UserRoomVO> findUserAndRoomId(String accountId);
    Optional<User> findUserNotJoined(String accountId);
    Optional<User> findUserAndFetchRoom(String accountId);
}