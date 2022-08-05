package com.project.tcg.global.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.project.tcg.domain.chat.facade.RoomUserFacade;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.security.jwt.JwtTokenProvider;
import com.project.tcg.global.socket.sercurity.ClientProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SocketConnectListener {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserFacade userFacade;

    private final RoomUserFacade roomUserFacade;

    private final SocketIOServer socketIOServer;

    @OnConnect
    public void onConnect(SocketIOClient socketIOClient) {

        String token = socketIOClient.getHandshakeData().getHttpHeaders().get("Authorization");
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        String accountId = authentication.getName();
        socketIOClient.set(ClientProperty.USER_KEY, accountId);
    }
}