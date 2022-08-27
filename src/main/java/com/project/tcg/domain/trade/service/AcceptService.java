package com.project.tcg.domain.trade.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.facade.CardFacade;
import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.chat.facade.RoomFacade;
import com.project.tcg.domain.chat.facade.RoomUserFacade;
import com.project.tcg.domain.trade.domain.Offer;
import com.project.tcg.domain.trade.exception.AcceptImpossibleException;
import com.project.tcg.domain.trade.presentation.dto.response.TradeResponse;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import com.project.tcg.global.socket.util.SocketUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AcceptService {

    private final RoomFacade roomFacade;
    private final UserFacade userFacade;
    private final RoomUserFacade roomUserFacade;
    private final CardFacade cardFacade;
    private final SocketIOServer socketIOServer;

    @Transactional
    public void execute(SocketIOClient socketIOClient) {

        User user = userFacade.getUserAndFetchRoom(SocketUtil.getAccountId(socketIOClient));
        RoomUser roomUser = user.getRoomUser();
        Room room = roomUser.getRoom();

        if (!room.isAllUserOffered() || room.getRoomUsers().size() < 2) {
            throw AcceptImpossibleException.EXCEPTION;
        }

        if (roomUser.getIsAccepted()){
            roomUser.cancelAccept();
        } else {
            roomUser.doAccept();
        }

        String socketRoomId = room.getId().toString();

        roomUserFacade.notifyAccept(roomUser, (acceptResponse) -> {
            socketIOServer.getRoomOperations(socketRoomId)
                    .sendEvent(SocketProperty.ACCEPT, acceptResponse);
        });

        if (isTradeable(room)) {

            doTrade(room);

            socketIOServer.getRoomOperations(socketRoomId)
                    .sendEvent(SocketProperty.TRADE, new TradeResponse("거래가 완료됐습니다"));

            roomUserFacade.cancelAllOffer(room);

            roomUserFacade.notifyAllOffer(room, (offerResponse) -> {
                socketIOServer.getRoomOperations(socketRoomId)
                        .sendEvent(SocketProperty.OFFER, offerResponse);
            });

            roomUserFacade.cancelAllAccept(room);

            roomUserFacade.notifyAllAccept(room, (acceptResponse) -> {
                socketIOServer.getRoomOperations(socketRoomId)
                        .sendEvent(SocketProperty.ACCEPT, acceptResponse);
            });
        }
    }

    private boolean isTradeable(Room room) {
        return room.isAllUserAccepted();
    }

    private void doTrade(Room room) {

        RoomUser roomUser1 = room.getRoomUsers().get(0);
        User user1 = roomUser1.getUser();
        Offer offer1 = roomUser1.getOffer();
        Card offerCard1 = cardFacade.getCardById(offer1.getCardId());

        RoomUser roomUser2 = room.getRoomUsers().get(1);
        User user2 = roomUser2.getUser();
        Offer offer2 = roomUser2.getOffer();
        Card offerCard2 = cardFacade.getCardById(offer2.getCardId());

        user1.giveResourcesToUser(offerCard1, offer1.getCardCount(), offer1.getCoin(), user2);
        user2.giveResourcesToUser(offerCard2, offer2.getCardCount(), offer2.getCoin(), user1);
    }
}