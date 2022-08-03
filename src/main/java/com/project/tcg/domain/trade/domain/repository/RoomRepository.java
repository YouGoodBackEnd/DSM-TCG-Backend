package com.project.tcg.domain.trade.domain.repository;

import com.project.tcg.domain.chat.domain.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findBy();
}