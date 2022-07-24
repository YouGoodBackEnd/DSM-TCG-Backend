package com.project.tcg.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.trade.domain.Room;
import com.project.tcg.domain.trade.facade.RoomFacade;
import com.project.tcg.domain.trade.facade.RoomUserFacade;
import com.project.tcg.domain.chat.controller.dto.request.ChatRequest;
import com.project.tcg.domain.chat.controller.dto.response.ChatResponse;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ChattingService {

    private final RoomFacade roomFacade;

    private final UserFacade userFacade;

    private final RoomUserFacade roomUserFacade;

    @Transactional
    public void execute(SocketIOClient socketIOClient, SocketIOServer server, ChatRequest request){

        Room room = roomFacade.getRoomById(Long.valueOf(request.getRoomId()));
        User user = userFacade.getUserByClient(socketIOClient);

        roomUserFacade.CheckRoomUserIsExist(room,user);

        ChatResponse response = ChatResponse.builder()
                .username(user.getName())
                .chat(request.getChat())
                .sentAt(LocalDateTime.now())
                .build();

        server.getRoomOperations(room.getId().toString())
                        .sendEvent(SocketProperty.CHAT, response);

        server.getRoomOperations(room.getId().toString())
                .sendEvent(SocketProperty.CHAT, response);
    }
}