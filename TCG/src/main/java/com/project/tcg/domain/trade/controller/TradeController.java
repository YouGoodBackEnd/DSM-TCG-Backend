package com.project.tcg.domain.trade.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.trade.controller.dto.request.SuggestRequest;
import com.project.tcg.domain.trade.controller.dto.response.SuggestDto;
import com.project.tcg.domain.trade.service.CancelAcceptService;
import com.project.tcg.domain.trade.service.CancelSuggestService;
import com.project.tcg.domain.trade.service.DoAcceptService;
import com.project.tcg.domain.trade.service.DoSuggestService;
import com.project.tcg.global.socket.annotation.SocketController;
import com.project.tcg.global.socket.annotation.SocketMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@SocketController
@RequiredArgsConstructor
@RestController
public class TradeController {

    private final DoSuggestService doSuggestService;

    private final CancelSuggestService cancelSuggestService;

    private final DoAcceptService doAcceptService;

    private final CancelAcceptService cancelAcceptService;

    @SocketMapping(endpoint = "suggest", requestCls = SuggestDto.class)
    public void doSuggest(SocketIOClient socketIOClient, SocketIOServer server, SuggestRequest request) {
        doSuggestService.execute(socketIOClient, server, request);
    }

    @SocketMapping(endpoint = "suggest")
    public void cancelSuggest(SocketIOClient socketIOClient, SocketIOServer server) {
        cancelSuggestService.execute(socketIOClient, server);
    }

}