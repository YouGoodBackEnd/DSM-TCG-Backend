package com.project.tcg.domain.chat.service;

import com.project.tcg.domain.chat.presentation.dto.response.QueryRoomListResponse;
import com.project.tcg.domain.chat.presentation.dto.response.RoomResponse;
import com.project.tcg.domain.trade.domain.repository.RoomRepository;
import com.project.tcg.domain.chat.facade.RoomFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryRoomListService {

    private final RoomRepository roomRepository;
    private final RoomFacade roomFacade;

    public QueryRoomListResponse execute(){

        return new QueryRoomListResponse(
                roomRepository.findBy()
                .stream()
                .filter(roomFacade::isNotEmptyRoom)
                .filter(roomFacade::isNotOverstaffedRoom)
                .map(RoomResponse::of)
                .collect(Collectors.toList()));
    }

}