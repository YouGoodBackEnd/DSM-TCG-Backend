package com.project.tcg.domain.chat.presentation;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.project.tcg.domain.chat.presentation.dto.request.ChatRequest;
import com.project.tcg.domain.chat.presentation.dto.request.CreateRoomRequest;
import com.project.tcg.domain.chat.presentation.dto.request.ParticipateRoomRequest;
import com.project.tcg.domain.chat.presentation.dto.response.QueryEmojiListResponse;
import com.project.tcg.domain.chat.presentation.dto.response.QueryRoomListResponse;
import com.project.tcg.domain.chat.service.SendChatService;
import com.project.tcg.domain.chat.service.CreateRoomService;
import com.project.tcg.domain.chat.service.LeaveRoomService;
import com.project.tcg.domain.chat.service.JoinRoomService;
import com.project.tcg.domain.chat.service.QueryEmojiListService;
import com.project.tcg.domain.chat.service.QueryRoomListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class ChatController {

    private final CreateRoomService createRoomService;
    private final JoinRoomService joinRoomService;
    private final SendChatService sendChatService;
    private final LeaveRoomService leaveRoomService;
    private final QueryRoomListService queryRoomListService;
    private final QueryEmojiListService queryEmojiListService;

    @OnEvent("create")
    public void createRoom(SocketIOClient socketIOClient, @RequestBody CreateRoomRequest request){
        createRoomService.execute(socketIOClient, request);
    }

    @OnEvent("participate")
    public void participateRoom(SocketIOClient socketIOClient, @RequestBody ParticipateRoomRequest request) {
        joinRoomService.execute(socketIOClient, request);
    }

    @OnEvent("chat")
    public void chatting(SocketIOClient socketIOClient, @RequestBody ChatRequest request) {
        sendChatService.execute(socketIOClient, request);
    }

    @OnEvent("leave")
    public void leaveRoom(SocketIOClient socketIOClient) {
        leaveRoomService.execute(socketIOClient);
    }

    @GetMapping("/rooms")
    public QueryRoomListResponse queryRoomList() {
        return queryRoomListService.execute();
    }

    @GetMapping("/emojis")
    public QueryEmojiListResponse queryEmojiList() {
        return queryEmojiListService.execute();
    }
}