package com.project.tcg.domain.chat.facade;

import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.chat.domain.repository.RoomUserRepository;
import com.project.tcg.domain.trade.domain.Offer;
import com.project.tcg.domain.trade.presentation.dto.response.AcceptResponse;
import com.project.tcg.domain.trade.presentation.dto.response.OfferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Component
public class RoomUserFacade {

    private final RoomUserRepository roomUserRepository;

    public void cancelAllOffer(Room room) {
        roomUserRepository.cancelAllOfferIn(room);
    }

    public void cancelAllAccept(Room room) {
        roomUserRepository.cancelAllAccept(room);
    }

    public void notifyAllOffer(Room room, Consumer<Object> sendEventConsumer) {
        room.getRoomUsers()
                .forEach(roomUser -> notifyOffer(roomUser, sendEventConsumer));
    }

    public void notifyOffer(RoomUser roomUser, Consumer<Object> sendEventConsumer) {

        Offer offer = roomUser.getOffer() != null ? roomUser.getOffer() : new Offer();

        OfferResponse response = OfferResponse
                .builder()
                .userId(roomUser.getId())
                .isOffered(roomUser.getIsOffered())
                .cardId(offer.getCardId())
                .cardCount(offer.getCardCount())
                .coin(offer.getCoin())
                .build();

        sendEventConsumer.accept(response);
    }

    public void notifyAllAccept(Room room, Consumer<Object> sendEventConsumer) {
        room.getRoomUsers()
                .forEach(roomUser ->
                        notifyAccept(roomUser, sendEventConsumer));
    }

    public void notifyAccept(RoomUser roomUser, Consumer<Object> sendEventConsumer){

        AcceptResponse response = AcceptResponse
                .builder()
                .userId(roomUser.getId())
                .isAccepted(roomUser.getIsAccepted())
                .build();

        sendEventConsumer.accept(response);
    }

}