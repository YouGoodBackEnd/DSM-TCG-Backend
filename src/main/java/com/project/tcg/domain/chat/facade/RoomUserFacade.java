package com.project.tcg.domain.chat.facade;

import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.chat.domain.repository.RoomUserRepository;
import com.project.tcg.domain.chat.exception.RoomNotFoundException;
import com.project.tcg.domain.chat.exception.RoomUserAlreadyExistException;
import com.project.tcg.domain.trade.domain.Offer;
import com.project.tcg.domain.trade.exception.OfferImpossibleException;
import com.project.tcg.domain.trade.presentation.dto.response.AcceptResponse;
import com.project.tcg.domain.trade.presentation.dto.response.OfferResponse;
import com.project.tcg.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Component
public class RoomUserFacade {

    private final RoomUserRepository roomUserRepository;

    public RoomUser getRoomUserByRoomAndUser(Room room, User user) {
        return roomUserRepository.findByRoomAndUser(room, user)
                .orElseThrow(() -> RoomNotFoundException.EXCEPTION);
    }

    public void removeParticipatingRooms(User user) {
        roomUserRepository.deleteAllByUser(user);
    }

    public void checkRoomUserIsNotExist(Room room, User user) {
        if (roomUserIsExist(room, user)){
            throw RoomUserAlreadyExistException.EXCEPTION;
        }
    }

    public void checkRoomUserIsExist(Room room, User user) {
        if (!roomUserIsExist(room, user)){
            throw RoomNotFoundException.EXCEPTION;
        }
    }

    private Boolean roomUserIsExist(Room room, User user) {
        return roomUserRepository.countByRoomAndUser(room, user) > 0;
    }

    public void checkAllRoomUsersIsNotAccepted(Room room) {
        if(roomUserRepository.countByRoomAndIsAcceptedTrue(room) > 0){
            throw OfferImpossibleException.EXCEPTION;
        }
    }

    public void makeAllRoomUserNotOfferedState(Room room) {
        room.getRoomUsers()
                .forEach(roomUser -> roomUser.setOffer(new Offer()));
    }

    public void makeAllRoomUserNotAcceptedState(Room room) {
        room.getRoomUsers()
                .forEach(RoomUser::cancelAccept);
    }

    public void notifyAllRoomUsersOfferState(Room room, Consumer<Object> sendEventConsumer) {
        room.getRoomUsers()
                .forEach(roomUser -> notifyRoomUserOfferState(roomUser, sendEventConsumer));
    }

    public void notifyRoomUserOfferState(RoomUser roomUser, Consumer<Object> sendEventConsumer) {

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

    public void notifyAllRoomUsersAcceptState(Room room, Consumer<Object> sendEventConsumer) {
        room.getRoomUsers()
                .forEach(roomUser ->
                        notifyRoomUserAcceptState(roomUser, sendEventConsumer));
    }

    public void notifyRoomUserAcceptState(RoomUser roomUser, Consumer<Object> sendEventConsumer){

        AcceptResponse response = AcceptResponse
                .builder()
                .userId(roomUser.getId())
                .isAccepted(roomUser.getIsAccepted())
                .build();

        sendEventConsumer.accept(response);
    }
}