package com.project.tcg.domain.trade.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.trade.controller.dto.request.AcceptRequest;
import com.project.tcg.domain.trade.controller.dto.response.AcceptResponse;
import com.project.tcg.domain.trade.domain.Room;
import com.project.tcg.domain.trade.domain.RoomUser;
import com.project.tcg.domain.trade.exception.DidNotOfferedException;
import com.project.tcg.domain.trade.facade.RoomFacade;
import com.project.tcg.domain.trade.facade.RoomUserFacade;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AcceptService {

    private final RoomFacade roomFacade;

    private final UserFacade userFacade;

    private final RoomUserFacade roomUserFacade;

    @Transactional
    public void execute(SocketIOClient socketIOClient, SocketIOServer server, AcceptRequest request) {

        Room room = roomFacade.getRoomById(request.getRoomId());
        User user = userFacade.getUserByClient(socketIOClient);

        RoomUser roomUser = roomUserFacade.getRoomUserByRoomAndUser(room, user);

        if (!room.checkBothOffered() || room.getRoomUsers().size() < 2) {
            throw DidNotOfferedException.EXCEPTION;
        }

        if (roomUser.getIsAccepted()) roomUser.cancelAccept();
        else roomUser.doAccept();
        AcceptResponse response = new AcceptResponse(roomUser.getIsAccepted());

        server.getRoomOperations(room.getId().toString())
                .sendEvent(SocketProperty.SUGGEST, response);

        if (isTradeable(room)) {
            doTrade(room);
            server.getRoomOperations(room.getId().toString())
                    .sendEvent(SocketProperty.TRADE);
        }
    }

    private boolean isTradeable(Room room) {
        return room.checkBothAccepted();
    }

    private void doTrade(Room room) {

    }
}