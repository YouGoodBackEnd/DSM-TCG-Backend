package com.project.tcg.domain.chat.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.chat.controller.dto.request.ChatRequest;
import com.project.tcg.domain.chat.controller.dto.request.CreateRoomRequest;
import com.project.tcg.domain.chat.controller.dto.request.ParticipateRoomRequest;
import com.project.tcg.domain.chat.controller.dto.response.QueryRoomResponse;
import com.project.tcg.domain.chat.service.ChattingService;
import com.project.tcg.domain.chat.service.CreateRoomService;
import com.project.tcg.domain.chat.service.ParticipateRoomService;
import com.project.tcg.domain.chat.service.QueryRoomService;
import com.project.tcg.global.socket.annotation.SocketController;
import com.project.tcg.global.socket.annotation.SocketMapping;
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
    public void chatting(SocketIOClient socketIOClient, SocketIOServer socketIOServer, ChatRequest request) {
        System.out.println("ChatController.chatting");
        chattingService.execute(socketIOClient, socketIOServer, request);
    }

    @SocketMapping(endpoint = "participate", requestCls = ParticipateRoomRequest.class)
    public void participateRoom(SocketIOClient socketIOClient, SocketIOServer socketIOServer, ParticipateRoomRequest request) {
        System.out.println("ChatController.participateRoom");
        participateRoomService.execute(socketIOClient, socketIOServer, request);
    }

    @SocketMapping(endpoint = "create", requestCls = CreateRoomRequest.class)
    public void createRoom(SocketIOClient socketIOClient, SocketIOServer socketIOServer, CreateRoomRequest request){
        System.out.println("ChatController.createRoom");
        createRoomService.execute(socketIOClient, socketIOServer, request);
    }

    @GetMapping("/rooms")
    public List<QueryRoomResponse> queryRoom(){
        System.out.println("ChatController.queryRoom");
        return queryRoomService.execute();
    }
}