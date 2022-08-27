package com.project.tcg.global.socket.util;

import com.corundumstudio.socketio.SocketIOClient;
import com.project.tcg.global.socket.sercurity.ClientProperty;
import org.springframework.stereotype.Component;

public class SocketUtil {

    public static String getAccountId(SocketIOClient socketIOClient) {
        return socketIOClient.get(ClientProperty.USER_KEY);
    }
}