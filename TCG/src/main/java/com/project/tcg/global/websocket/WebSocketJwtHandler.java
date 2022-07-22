package com.project.tcg.global.websocket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.security.jwt.JwtTokenProvider;
import com.project.tcg.global.websocket.sercurity.ClientProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WebSocketJwtHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;

    public static final ConcurrentMap<String, SocketIOClient> socketIOClientMap = new ConcurrentHashMap<>();

    @OnConnect
    public void onConnect(SocketIOClient client) {

        String token = client.getHandshakeData().getHttpHeaders().get("Authorization");
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        String accountId = authentication.getName();
        socketIOClientMap.put(accountId, client);
        client.set(ClientProperty.USER_KEY, accountId);
    }

    @OnDisconnect
    public void onDisConnect(SocketIOClient client) {

        socketIOClientMap.remove(client.get(ClientProperty.USER_KEY).toString());

    }

}