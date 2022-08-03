package com.project.tcg.domain.chat.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.project.tcg.domain.chat.controller.dto.request.ChatRequest;
import com.project.tcg.domain.chat.controller.dto.request.CreateRoomRequest;
import com.project.tcg.domain.chat.controller.dto.request.ParticipateRoomRequest;
import com.project.tcg.domain.chat.controller.dto.response.QueryRoomResponse;
import com.project.tcg.domain.chat.service.ChattingService;
import com.project.tcg.domain.chat.service.CreateRoomService;
import com.project.tcg.domain.chat.service.ParticipateRoomService;
import com.project.tcg.domain.chat.service.QueryRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChattingService chattingService;

    private final ParticipateRoomService participateRoomService;

    private final CreateRoomService createRoomService;

    private final QueryRoomService queryRoomService;

    @OnEvent("chat")
    public void chatting(SocketIOClient socketIOClient, SocketIOServer socketIOServer, @RequestBody ChatRequest request) {
        System.out.println("ChatController.chatting");
        chattingService.execute(socketIOClient, socketIOServer, request);
    }

    @OnEvent("participate")
    public void participateRoom(SocketIOClient socketIOClient, SocketIOServer socketIOServer, @RequestBody ParticipateRoomRequest request) {
        System.out.println("ChatController.participateRoom");
        participateRoomService.execute(socketIOClient, socketIOServer, request);
    }

    @OnEvent("create")
    public void createRoom(SocketIOClient socketIOClient, SocketIOServer socketIOServer, @RequestBody CreateRoomRequest request){
        System.out.println("ChatController.createRoom");
        createRoomService.execute(socketIOClient, socketIOServer, request);
    }

    @GetMapping("/rooms")
    public List<QueryRoomResponse> queryRoom(){
        System.out.println("ChatController.queryRoom");
        return queryRoomService.execute();
    }
}