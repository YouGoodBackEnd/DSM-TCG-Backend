package com.project.tcg.global.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.project.tcg.global.security.jwt.JwtTokenProvider;
import com.project.tcg.global.socket.sercurity.ClientProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WebSocketOnConnectListener implements ConnectListener {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onConnect(SocketIOClient socketIOClient) {

        String token = socketIOClient.getHandshakeData().getHttpHeaders().get("Authorization");
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        String accountId = authentication.getName();
        socketIOClient.set(ClientProperty.USER_KEY, accountId);
    }
}