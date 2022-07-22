package com.project.tcg.domain.trade.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.trade.controller.dto.request.ChatRequest;
import com.project.tcg.domain.trade.controller.dto.request.CreateRoomRequest;
import com.project.tcg.domain.trade.controller.dto.request.ParticipateRoomRequest;
import com.project.tcg.domain.trade.controller.dto.response.QueryRoomResponse;
import com.project.tcg.domain.trade.service.ChattingService;
import com.project.tcg.domain.trade.service.CreateRoomService;
import com.project.tcg.domain.trade.service.ParticipateRoomService;
import com.project.tcg.domain.trade.service.QueryRoomService;
import com.project.tcg.global.websocket.annotation.SocketController;
import com.project.tcg.global.websocket.annotation.SocketMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SocketController
@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChattingService chattingService;

    private final ParticipateRoomService participateRoomService;

    private final CreateRoomService createRoomService;

    private final QueryRoomService queryRoomService;

    @SocketMapping(endpoint = "chat", requestCls = ChatRequest.class)
    public void chatting(SocketIOClient socketIOClient, SocketIOServer server, ChatRequest request) {
        chattingService.execute(socketIOClient, server, request);
    }

    @SocketMapping(endpoint = "participate", requestCls = ParticipateRoomRequest.class)
    public void participateRoom(SocketIOClient socketIOClient, SocketIOServer server, ParticipateRoomRequest request) {
        participateRoomService.execute(socketIOClient, server, request);
    }

    @SocketMapping(endpoint = "create", requestCls = CreateRoomRequest.class)
    public void createRoom(SocketIOClient socketIOClient, SocketIOServer server, CreateRoomRequest request){
        createRoomService.execute(socketIOClient, server, request);
    }

    @GetMapping("/room")
    public List<QueryRoomResponse> queryRoom(){
        return queryRoomService.execute();
    }
}