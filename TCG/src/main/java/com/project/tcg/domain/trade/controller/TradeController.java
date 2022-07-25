package com.project.tcg.domain.trade.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.trade.controller.dto.request.AcceptRequest;
import com.project.tcg.domain.trade.controller.dto.request.OfferRequest;
import com.project.tcg.domain.trade.service.AcceptService;
import com.project.tcg.domain.trade.service.OfferService;
import com.project.tcg.global.socket.annotation.SocketController;
import com.project.tcg.global.socket.annotation.SocketMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@SocketController
@RequiredArgsConstructor
@RestController
public class TradeController {

    private final OfferService offerService;

    private final AcceptService acceptService;

    @SocketMapping(endpoint = "suggest", requestCls = OfferRequest.class)
    public void suggest(SocketIOClient socketIOClient, SocketIOServer server, OfferRequest request) {
        offerService.execute(socketIOClient, server, request);
    }

    @SocketMapping(endpoint = "accept", requestCls = AcceptRequest.class)
    public void accept(SocketIOClient socketIOClient, SocketIOServer server, AcceptRequest request) {
        acceptService.execute(socketIOClient, server, request);
    }

}