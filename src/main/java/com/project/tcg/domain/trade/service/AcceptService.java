package com.project.tcg.domain.trade.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.facade.CardFacade;
import com.project.tcg.domain.trade.presentation.dto.request.AcceptRequest;
import com.project.tcg.domain.trade.presentation.dto.response.AcceptResponse;
import com.project.tcg.domain.trade.presentation.dto.response.TradeResponse;
import com.project.tcg.domain.trade.domain.Offer;
import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.trade.domain.repository.RoomUserRepository;
import com.project.tcg.domain.trade.exception.DidNotOfferedException;
import com.project.tcg.domain.chat.facade.RoomFacade;
import com.project.tcg.domain.chat.facade.RoomUserFacade;
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
    private final CardFacade cardFacade;
    private final RoomUserRepository roomUserRepository;
    private final SocketIOServer socketIOServer;

    @Transactional
    public void execute(SocketIOClient socketIOClient, AcceptRequest request) {

        Room room = roomFacade.getRoomById(request.getRoomId());
        User user = userFacade.getUserByClient(socketIOClient);

        RoomUser roomUser = roomUserFacade.getRoomUserByRoomAndUser(room, user);

        if (!room.checkBothOffered() || room.getRoomUsers().size() < 2) {
            throw DidNotOfferedException.EXCEPTION;
        }

        if (roomUser.getIsAccepted()) roomUser.cancelAccept();
        else roomUser.doAccept();
        roomUserRepository.save(roomUser);

        AcceptResponse response = new AcceptResponse(user.getId(), roomUser.getIsAccepted());

        socketIOServer.getRoomOperations(room.getId().toString())
                .sendEvent(SocketProperty.ACCEPT, response);

        if (isTradeable(room)) {
            doTrade(room);
            socketIOServer.getRoomOperations(room.getId().toString())
                    .sendEvent(SocketProperty.TRADE, new TradeResponse("거래가 완료됐습니다"));
        }
    }

    private boolean isTradeable(Room room) {
        return room.checkBothAccepted();
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

        user1.giveResourcesToUser(offerCard1, offer1.getCoin(), user2);
        user2.giveResourcesToUser(offerCard2, offer2.getCoin(), user1);
    }

}