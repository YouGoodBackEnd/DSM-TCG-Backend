package com.project.tcg.domain.trade.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.card.facade.UserCardFacade;
import com.project.tcg.domain.trade.controller.dto.request.SuggestRequest;
import com.project.tcg.domain.trade.controller.dto.response.SuggestDto;
import com.project.tcg.domain.trade.domain.Room;
import com.project.tcg.domain.trade.domain.RoomUser;
import com.project.tcg.domain.trade.domain.repository.RoomUserRepository;
import com.project.tcg.domain.trade.facade.RoomFacade;
import com.project.tcg.domain.trade.facade.RoomUserFacade;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CancelSuggestService {
    private final RoomFacade roomFacade;

    private final UserFacade userFacade;

    private final RoomUserFacade roomUserFacade;

    private final UserCardFacade userCardFacade;

    private final RoomUserRepository roomUserRepository;

    public void execute(SocketIOClient socketIOClient, SocketIOServer server, SuggestRequest request) {

        Room room = roomFacade.getRoomById(request.getRoomId());
        User user = userFacade.getUserByClient(socketIOClient);

        RoomUser roomUser = roomUserFacade.getRoomUserByRoomAndUser(room, user);

        roomUser.cancelSuggest();

        SuggestDto response = SuggestDto
                .builder()
                .cardId(null)
                .coin(null)
                .build();

        server.getRoomOperations(room.getId().toString())
                .sendEvent(SocketProperty.SUGGEST, response);
    }
}