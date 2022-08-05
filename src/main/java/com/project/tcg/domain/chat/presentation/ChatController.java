package com.project.tcg.domain.chat.presentation;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.project.tcg.domain.chat.presentation.dto.request.ChatRequest;
import com.project.tcg.domain.chat.presentation.dto.request.CreateRoomRequest;
import com.project.tcg.domain.chat.presentation.dto.request.ParticipateRoomRequest;
import com.project.tcg.domain.chat.presentation.dto.response.QueryRoomListResponse;
import com.project.tcg.domain.chat.service.ChattingService;
import com.project.tcg.domain.chat.service.CreateRoomService;
import com.project.tcg.domain.chat.service.LeaveRoomService;
import com.project.tcg.domain.chat.service.ParticipateRoomService;
import com.project.tcg.domain.chat.service.QueryRoomListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class ChatController {

    private final CreateRoomService createRoomService;
    private final ParticipateRoomService participateRoomService;
    private final ChattingService chattingService;
    private final LeaveRoomService leaveRoomService;
    private final QueryRoomListService queryRoomListService;

    @OnEvent("create")
    public void createRoom(SocketIOClient socketIOClient, @RequestBody CreateRoomRequest request){

        createRoomService.execute(socketIOClient, request);
    }

    @OnEvent("participate")
    public void participateRoom(SocketIOClient socketIOClient, @RequestBody ParticipateRoomRequest request) {
        participateRoomService.execute(socketIOClient, request);
    }

    @OnEvent("chat")
    public void chatting(SocketIOClient socketIOClient, @RequestBody ChatRequest request) {
        chattingService.execute(socketIOClient, request);
    }

    @OnEvent("leave")
    public void leaveRoom(SocketIOClient socketIOClient) {
        leaveRoomService.execute(socketIOClient);
    }

    @GetMapping("/rooms")
    public QueryRoomListResponse queryRoom(){
        return queryRoomListService.execute();
    }
}