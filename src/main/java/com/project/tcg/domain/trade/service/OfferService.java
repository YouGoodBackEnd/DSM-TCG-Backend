package com.project.tcg.domain.trade.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.chat.facade.RoomFacade;
import com.project.tcg.domain.chat.facade.RoomUserFacade;
import com.project.tcg.domain.trade.domain.Offer;
import com.project.tcg.domain.trade.facade.OfferFacade;
import com.project.tcg.domain.trade.presentation.dto.request.OfferRequest;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OfferService {

    private final RoomFacade roomFacade;
    private final UserFacade userFacade;
    private final RoomUserFacade roomUserFacade;
    private final OfferFacade offerFacade;
    private final SocketIOServer socketIOServer;

    @Transactional
    public void execute(SocketIOClient socketIOClient, OfferRequest request) {

        Room room = roomFacade.getRoomById(request.getRoomId());
        User user = userFacade.getUserByClient(socketIOClient);

        RoomUser roomUser = roomUserFacade.getRoomUserByRoomAndUser(room, user);

        roomUserFacade.checkAllRoomUsersIsNotAccepted(room);

        Long suggestCardId = request.getCardId();
        Integer suggestCardCount = request.getCardCount();
        Integer suggestCoin = request.getCoin();

        Offer offer = offerFacade.validateAndGetOffer(suggestCardId, suggestCardCount, suggestCoin, user);

        if(roomUser.setOffer(offer)){
            roomUserFacade.notifyRoomUserOfferState(room.getId(), roomUser, (String roomId, Object offerResponse) -> {
                socketIOServer.getRoomOperations(roomId)
                        .sendEvent(SocketProperty.OFFER, offerResponse);
            });
        }
    }
}