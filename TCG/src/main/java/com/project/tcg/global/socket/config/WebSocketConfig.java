package com.project.tcg.global.socket.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.project.tcg.domain.trade.facade.RoomUserFacade;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.security.jwt.JwtTokenProvider;
import com.project.tcg.global.socket.WebSocketAddMappingSupporter;
import com.project.tcg.global.socket.WebSocketDisConnectListener;
import com.project.tcg.global.socket.WebSocketOnConnectListener;
import com.project.tcg.global.socket.exception.SocketExceptionListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketConfig {

    @Value("${socket.port}")
    private Integer port;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserFacade userFacade;

    private final RoomUserFacade roomUserFacade;

    private final WebSocketAddMappingSupporter webSocketAddMappingSupporter;

    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);

        Configuration configuration = new Configuration();
        configuration.setPort(port);
        configuration.setOrigin("*");
        configuration.setSocketConfig(socketConfig);
        configuration.setExceptionListener(new SocketExceptionListener());

        SocketIOServer socketIOServer = new SocketIOServer(configuration);
        socketIOServer.addConnectListener(new WebSocketOnConnectListener(jwtTokenProvider));
        socketIOServer.addDisconnectListener(new WebSocketDisConnectListener(userFacade, roomUserFacade, socketIOServer));
        webSocketAddMappingSupporter.addListeners(socketIOServer);
        return socketIOServer;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }

}