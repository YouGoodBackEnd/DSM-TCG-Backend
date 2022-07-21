package com.project.tcg.global.websocket.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.project.tcg.global.websocket.WebSocketAddMappingSupporter;
import com.project.tcg.global.websocket.exception.SocketExceptionListener;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketConfig {

    @Value("${socket.port}")
    private Integer port;

    private final WebSocketAddMappingSupporter mappingSupporter;
    private final SocketExceptionListener exceptionListener;

    @Bean
    public SocketIOServer socketIOServer() {

        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);

        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setPort(port);
        config.setSocketConfig(socketConfig);
        config.setOrigin("*");
        config.setExceptionListener(exceptionListener);

        //SocketIOServer server = new SocketIOServer(config);
        //mappingSupporter.addListeners(server);
        return new SocketIOServer(config);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }

}