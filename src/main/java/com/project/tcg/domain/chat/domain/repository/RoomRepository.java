package com.project.tcg.domain.chat.domain.repository;

import com.project.tcg.domain.chat.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findBy();
}