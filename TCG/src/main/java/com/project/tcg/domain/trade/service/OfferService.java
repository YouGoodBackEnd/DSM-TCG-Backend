package com.project.tcg.domain.trade.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.card.exception.CardNotFoundException;
import com.project.tcg.domain.card.facade.UserCardFacade;
import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.trade.controller.dto.request.OfferRequest;
import com.project.tcg.domain.trade.controller.dto.response.OfferResponse;
import com.project.tcg.domain.trade.domain.Offer;
import com.project.tcg.domain.trade.domain.repository.RoomUserRepository;
import com.project.tcg.domain.trade.exception.AlreadyAcceptedException;
import com.project.tcg.domain.trade.exception.CardLackException;
import com.project.tcg.domain.trade.exception.CoinLackException;
import com.project.tcg.domain.trade.facade.RoomFacade;
import com.project.tcg.domain.trade.facade.RoomUserFacade;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OfferService {

    private final RoomFacade roomFacade;

    private final UserFacade userFacade;

    private final RoomUserFacade roomUserFacade;

    private final UserCardFacade userCardFacade;

    private final RoomUserRepository roomUserRepository;

    public void execute(SocketIOClient socketIOClient, SocketIOServer server, OfferRequest request) {

        Room room = roomFacade.getRoomById(request.getRoomId());
        User user = userFacade.getUserByClient(socketIOClient);

        RoomUser roomUser = roomUserFacade.getRoomUserByRoomAndUser(room, user);

        if(room.isAcceptedAnyone()){
            throw AlreadyAcceptedException.EXCEPTION;
        }

        Long suggestCardId = request.getCardId();
        Integer suggestCardCount = request.getCardCount();
        validateCard(suggestCardId, suggestCardCount, user);

        Integer suggestCoin = request.getCoin();
        validateCoin(suggestCoin, user);

        Offer offer = Offer.builder()
                .cardId(suggestCardId)
                .coin(suggestCoin)
                .build();

        roomUser.setOffer(offer);
        roomUserRepository.save(roomUser);

        OfferResponse response = OfferResponse
                .builder()
                .userId(user.getId())
                .isOffered(roomUser.getIsOffered())
                .cardId(suggestCardId)
                .cardCount(suggestCardCount)
                .coin(suggestCoin)
                .build();

        server.getRoomOperations(room.getId().toString())
                .sendEvent(SocketProperty.OFFER, response);
    }

    private void validateCard(Long cardId, Integer cardCount, User user) {
        if (cardId != null) {
            if (!userCardFacade.checkUserCardExist(user, cardId)) {
                throw CardNotFoundException.EXCEPTION;
            } else if (userCardFacade.getUserCardCount(cardId, user) < cardCount) {
                throw CardLackException.EXCEPTION;
            }
        }
    }

    private void validateCoin(Integer coin, User user) {
        if (coin != null) {
            if (user.getCoin() < coin) {
                throw CoinLackException.EXCEPTION;
            }
        }
    }

}