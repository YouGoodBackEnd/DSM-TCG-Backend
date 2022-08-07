package com.project.tcg.domain.chat.service;

import com.project.tcg.domain.chat.domain.repository.RoomRepository;
import com.project.tcg.domain.chat.facade.RoomFacade;
import com.project.tcg.domain.chat.presentation.dto.response.QueryRoomListResponse;
import com.project.tcg.domain.chat.presentation.dto.response.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryRoomListService {

    private final RoomRepository roomRepository;
    private final RoomFacade roomFacade;

    public QueryRoomListResponse execute() {

        List<RoomResponse> roomList =
                roomRepository.findBy()
                        .stream()
                        .filter(roomFacade::isNotEmptyRoom)
                        .filter(roomFacade::isNotFulledRoom)
                        .map(RoomResponse::of)
                        .collect(Collectors.toList());

        return new QueryRoomListResponse(roomList);
    }
}