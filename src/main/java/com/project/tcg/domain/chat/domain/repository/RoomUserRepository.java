package com.project.tcg.domain.chat.domain.repository;

import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomUserRepository extends CrudRepository<RoomUser, Long>, CustomRoomUserRepository {

    Optional<RoomUser> findByRoomAndUser(Room room, User user);
}