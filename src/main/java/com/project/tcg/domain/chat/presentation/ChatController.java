package com.project.tcg.domain.chat.presentation;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.project.tcg.domain.chat.presentation.dto.request.ChatRequest;
import com.project.tcg.domain.chat.presentation.dto.request.CreateRoomRequest;
import com.project.tcg.domain.chat.presentation.dto.request.ParticipateRoomRequest;
import com.project.tcg.domain.chat.presentation.dto.response.QueryRoomListResponse;
import com.project.tcg.domain.chat.service.ChattingService;
import com.project.tcg.domain.chat.service.CreateRoomService;
import com.project.tcg.domain.chat.service.ParticipateRoomService;
import com.project.tcg.domain.chat.service.QueryRoomListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChattingService chattingService;

    private final ParticipateRoomService participateRoomService;

    private final CreateRoomService createRoomService;

    private final QueryRoomListService queryRoomListService;

    @OnEvent("chat")
    public void chatting(SocketIOClient socketIOClient, SocketIOServer socketIOServer, @RequestBody ChatRequest request) {
        chattingService.execute(socketIOClient, socketIOServer, request);
    }

    @OnEvent("participate")
    public void participateRoom(SocketIOClient socketIOClient, SocketIOServer socketIOServer, @RequestBody ParticipateRoomRequest request) {
        participateRoomService.execute(socketIOClient, socketIOServer, request);
    }

    @OnEvent("create")
    public void createRoom(SocketIOClient socketIOClient, SocketIOServer socketIOServer, @RequestBody CreateRoomRequest request){
        createRoomService.execute(socketIOClient, socketIOServer, request);
    }

    @GetMapping("/rooms")
    public QueryRoomListResponse queryRoom(){
        return queryRoomListService.execute();
    }
}