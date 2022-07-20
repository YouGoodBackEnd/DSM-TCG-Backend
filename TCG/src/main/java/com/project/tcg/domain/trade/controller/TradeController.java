package com.project.tcg.domain.trade.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.project.tcg.domain.trade.controller.dto.request.ChatRequest;
import com.project.tcg.domain.trade.service.ChattingService;
import com.project.tcg.global.websocket.property.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TradeController {

    private final ChattingService chattingService;

    @OnEvent(SocketProperty.CHAT)
    public void chatting(SocketIOClient socketIOClient, SocketIOServer server, ChatRequest request) {
        chattingService.execute(socketIOClient, server, request);
    }
}