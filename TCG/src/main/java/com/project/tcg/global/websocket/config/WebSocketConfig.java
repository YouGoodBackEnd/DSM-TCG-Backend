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

    @Bean
    public SocketIOServer socketIOServer() {

        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);

        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setPort(port);
        config.setOrigin("*");
        config.setSocketConfig(socketConfig);
        config.setExceptionListener(new SocketExceptionListener());

        SocketIOServer server = new SocketIOServer(config);
        mappingSupporter.addListeners(server);
        return server;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer server) {
        return new SpringAnnotationScanner(server);
    }

}