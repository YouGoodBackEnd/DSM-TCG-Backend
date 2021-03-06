package com.project.tcg.domain.user.facade;

import com.corundumstudio.socketio.SocketIOClient;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.domain.repository.UserRepository;
import com.project.tcg.domain.user.exception.UserNotFoundException;
import com.project.tcg.global.websocket.sercurity.ClientProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {

        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByAccountId(accountId);
    }

    public User getUserByAccountId(String id) {

        return userRepository.findByAccountId(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getUserById(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getUserByClient(SocketIOClient client) {

        return userRepository.findByAccountId(client.get(ClientProperty.USER_KEY))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}