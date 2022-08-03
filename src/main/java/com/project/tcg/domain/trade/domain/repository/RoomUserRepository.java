package com.project.tcg.domain.trade.domain.repository;

import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoomUserRepository extends CrudRepository<RoomUser, Long> {

    List<RoomUser> findByUser(User user);

    Optional<RoomUser> findByRoomAndUser(Room room, User user);
}