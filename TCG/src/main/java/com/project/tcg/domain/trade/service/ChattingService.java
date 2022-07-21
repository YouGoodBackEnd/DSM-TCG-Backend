package com.project.tcg.domain.trade.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.trade.controller.dto.request.ChatRequest;
import com.project.tcg.domain.trade.controller.dto.response.ChatResponse;
import com.project.tcg.domain.trade.exception.SocketClientNotFoundException;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.websocket.SocketProperty;
import com.project.tcg.global.websocket.WebSocketJwtHandler;
import com.project.tcg.global.websocket.sercurity.SocketAuthProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChattingService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(SocketIOClient socketIOClient, SocketIOServer server, ChatRequest request){

        User user = userFacade.getUserByAccountId(socketIOClient.get(SocketAuthProperty.USER_KEY));

        User targetUser = userFacade.getUserById(request.getUserId());

        SocketIOClient clientToSend = Optional.ofNullable(WebSocketJwtHandler.socketIOClientMap
                        .get(targetUser.getAccountId()))
                .orElseThrow(() -> SocketClientNotFoundException.EXCEPTION);

        ChatResponse response = ChatResponse.builder()
                .username(user.getName())
                .chat(request.getChat()).build();

        clientToSend.sendEvent(SocketProperty.CHAT, response);

    }
}