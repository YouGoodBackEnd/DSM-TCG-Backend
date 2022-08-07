package com.project.tcg.global.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.project.tcg.domain.chat.facade.RoomUserFacade;
import com.project.tcg.domain.user.domain.User;
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

    @OnConnect
    public void onConnect(SocketIOClient socketIOClient) {

        String token = jwtTokenProvider.resolveToken(socketIOClient);

        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        String accountId = authentication.getName();
        socketIOClient.set(ClientProperty.USER_KEY, accountId);
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient socketIOClient) {

        User user = userFacade.getUserByClient(socketIOClient);

        roomUserFacade.removeParticipatingRooms(user);
    }
}