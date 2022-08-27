package com.project.tcg.domain.trade.presentation;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.project.tcg.domain.trade.presentation.dto.request.OfferRequest;
import com.project.tcg.domain.trade.service.AcceptService;
import com.project.tcg.domain.trade.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TradeController {

    private final OfferService offerService;

    private final AcceptService acceptService;

    @OnEvent("offer")
    public void suggest(SocketIOClient socketIOClient, @RequestBody OfferRequest request) {
        offerService.execute(socketIOClient, request);
    }

    @OnEvent("accept")
    public void accept(SocketIOClient socketIOClient) {
        acceptService.execute(socketIOClient);
    }

}