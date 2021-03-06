package com.project.tcg.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.chat.controller.dto.request.ChatRequest;
import com.project.tcg.domain.chat.controller.dto.response.ChatResponse;
import com.project.tcg.domain.chat.domain.enums.EmojiType;
import com.project.tcg.domain.chat.domain.repository.EmojiRepository;
import com.project.tcg.domain.chat.exception.EmojiNotFoundException;
import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.trade.facade.RoomFacade;
import com.project.tcg.domain.trade.facade.RoomUserFacade;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@RequiredArgsConstructor
@Service
public class ChattingService {

    private final RoomFacade roomFacade;

    private final UserFacade userFacade;

    private final EmojiRepository emojiRepository;

    private final RoomUserFacade roomUserFacade;

    @Transactional
    public void execute(SocketIOClient socketIOClient, SocketIOServer socketIOServer, ChatRequest request){

        Room room = roomFacade.getRoomById(request.getRoomId());
        User user = userFacade.getUserByClient(socketIOClient);

        roomUserFacade.CheckRoomUserIsExist(room,user);

        String emojiImageUrl = getEmojiImageUrl(request.getEmojiType());

        ChatResponse response = ChatResponse.builder()
                .userId(user.getId())
                .profileImageUrl(user.getProfileImageUrl())
                .username(user.getName())
                .emojiImageUrl(emojiImageUrl)
                .chat(request.getChat())
                .sentAt(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)))
                .build();

        socketIOServer.getRoomOperations(room.getId().toString())
                        .sendEvent(SocketProperty.CHAT, response);
    }

    private String getEmojiImageUrl(EmojiType emojiType) {
        if(emojiType == null) return null;
        else return emojiRepository.findByEmojiType(emojiType)
                .orElseThrow(()-> EmojiNotFoundException.EXCEPTION)
                .getEmojiImageUrl();
    }
}