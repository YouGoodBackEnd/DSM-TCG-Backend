package com.project.tcg.global.websocket;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SocketRunner implements CommandLineRunner {
    //스프링 부트 구동 시점에 실행

    private final SocketIOServer socketIOServer;

    @Override
    public void run(String... args) {
        socketIOServer.start();
    }

}