package com.project.tcg.domain.chat.service;

import com.project.tcg.domain.trade.domain.repository.RoomRepository;
import com.project.tcg.domain.trade.facade.RoomFacade;
import com.project.tcg.domain.chat.controller.dto.response.QueryRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryRoomService {

    private final RoomRepository roomRepository;

    private final RoomFacade roomFacade;

    public List<QueryRoomResponse> execute(){

        return roomRepository.findBy()
                .stream()
                .filter(roomFacade::isNotEmptyRoom)
                .filter(roomFacade::isNotOverstaffedRoom)
                .map(QueryRoomResponse::of)
                .collect(Collectors.toList());
    }

}