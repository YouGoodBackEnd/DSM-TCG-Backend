package com.project.tcg.domain.trade.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.chat.facade.RoomFacade;
import com.project.tcg.domain.chat.facade.RoomUserFacade;
import com.project.tcg.domain.trade.domain.Offer;
import com.project.tcg.domain.trade.exception.OfferImpossibleException;
import com.project.tcg.domain.trade.facade.OfferFacade;
import com.project.tcg.domain.trade.presentation.dto.request.OfferRequest;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import com.project.tcg.global.socket.util.SocketUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OfferService {

    private final UserFacade userFacade;
    private final RoomUserFacade roomUserFacade;
    private final OfferFacade offerFacade;
    private final SocketIOServer socketIOServer;

    @Transactional
    public void execute(SocketIOClient socketIOClient, OfferRequest request) {

        User user = userFacade.getUserAndFetchRoom(SocketUtil.getAccountId(socketIOClient));
        RoomUser roomUser = user.getRoomUser();
        Room room = roomUser.getRoom();

        if(room.isUserAccepted()){
            throw OfferImpossibleException.EXCEPTION;
        }

        Long suggestCardId = request.getCardId();
        Integer suggestCardCount = request.getCardCount();
        Integer suggestCoin = request.getCoin();

        Offer offer = offerFacade.validateAndGetOffer(suggestCardId, suggestCardCount, suggestCoin, user);

        if(roomUser.setOffer(offer)){

            String socketRoomId = room.getId().toString();

            roomUserFacade.notifyOffer(roomUser, offerResponse -> {
                socketIOServer.getRoomOperations(socketRoomId)
                        .sendEvent(SocketProperty.OFFER, offerResponse);
            });
        }
    }
}