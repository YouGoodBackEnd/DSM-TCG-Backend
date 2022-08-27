package com.project.tcg.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.chat.domain.repository.EmojiRepository;
import com.project.tcg.domain.chat.domain.repository.vo.UserRoomVO;
import com.project.tcg.domain.chat.exception.EmojiNotFoundException;
import com.project.tcg.domain.chat.exception.RoomNotFoundException;
import com.project.tcg.domain.chat.presentation.dto.request.ChatRequest;
import com.project.tcg.domain.chat.presentation.dto.response.ChatResponse;
import com.project.tcg.domain.user.domain.repository.UserRepository;
import com.project.tcg.global.socket.SocketProperty;
import com.project.tcg.global.socket.util.SocketUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@RequiredArgsConstructor
@Service
public class SendChatService {

    private final UserRepository userRepository;
    private final EmojiRepository emojiRepository;
    private final SocketIOServer socketIOServer;

    @Transactional
    public void execute(SocketIOClient socketIOClient, ChatRequest request){

        UserRoomVO vo = userRepository.findUserAndRoomId(SocketUtil.getAccountId(socketIOClient))
                .orElseThrow(()-> RoomNotFoundException.EXCEPTION);

        String emojiImageUrl = getEmojiImageUrl(request.getEmojiId());

        ChatResponse response = ChatResponse.builder()
                .userId(vo.getUserId())
                .profileImageUrl(vo.getProfileImageUrl())
                .username(vo.getUserName())
                .emojiImageUrl(emojiImageUrl)
                .chat(request.getChat())
                .sentAt(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)))
                .build();

        String socketRoomId = vo.getRoomId().toString();
        socketIOServer.getRoomOperations(socketRoomId)
                        .sendEvent(SocketProperty.CHAT, response);
    }

    private String getEmojiImageUrl(Long emojiId) {
        if(emojiId == null) {
            return null;
        } else {
            return emojiRepository.findById(emojiId)
                    .orElseThrow(()-> EmojiNotFoundException.EXCEPTION)
                    .getEmojiImageUrl();
        }
    }
}