package com.project.tcg.global.socket.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.project.tcg.global.socket.WebSocketAddMappingSupporter;
import com.project.tcg.global.socket.exception.SocketExceptionListener;
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

        Configuration config = new Configuration();
        config.setPort(port);
        config.setOrigin("*");
        config.setSocketConfig(socketConfig);
        config.setExceptionListener(new SocketExceptionListener());

        SocketIOServer server = new SocketIOServer(config);
        mappingSupporter.addListeners(server);
        return server;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner() {
        return new SpringAnnotationScanner(socketIOServer());
    }

}