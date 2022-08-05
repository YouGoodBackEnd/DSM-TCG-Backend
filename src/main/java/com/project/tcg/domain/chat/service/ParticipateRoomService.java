package com.project.tcg.domain.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.chat.presentation.dto.request.ParticipateRoomRequest;
import com.project.tcg.domain.chat.presentation.dto.response.RoomNotificationResponse;
import com.project.tcg.domain.trade.domain.Offer;
import com.project.tcg.domain.trade.domain.repository.RoomUserRepository;
import com.project.tcg.domain.trade.exception.OverstaffedRoomException;
import com.project.tcg.domain.chat.facade.RoomFacade;
import com.project.tcg.domain.chat.facade.RoomUserFacade;
import com.project.tcg.domain.trade.presentation.dto.response.AcceptResponse;
import com.project.tcg.domain.trade.presentation.dto.response.OfferResponse;
import com.project.tcg.domain.user.domain.User;
import com.project.tcg.domain.user.facade.UserFacade;
import com.project.tcg.global.socket.SocketProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ParticipateRoomService {

    private final RoomUserRepository roomUserRepository;

    private final RoomFacade roomFacade;

    private final RoomUserFacade roomUserFacade;

    private final UserFacade userFacade;

    private final SocketIOServer socketIOServer;

    @Transactional
    public void execute(SocketIOClient socketIOClient, ParticipateRoomRequest request) {

        Room room = roomFacade.getRoomById(request.getRoomId());
        User user = userFacade.getUserByClient(socketIOClient);

        roomUserFacade.removeParticipatingRooms(user);
        socketIOClient
                .getAllRooms()
                .forEach(socketIOClient::leaveRoom);

        if (2 <= room.getRoomUsers().size())
            throw OverstaffedRoomException.EXCEPTION;

        roomUserRepository.save(RoomUser.builder()
                .room(room)
                .user(user)
                .isAccepted(false)
                .isOffered(false)
                .offer(null)
                .build()
        );

        socketIOClient.joinRoom(room.getId().toString());

        notifyRoomUsersOfferState(room);
        makeRoomUserIsNotAcceptedState(room);

        RoomNotificationResponse response =
                new RoomNotificationResponse(room.getId(), user.getName() + "님이 입장하셨습니다");

        socketIOServer.getRoomOperations(room.getId().toString())
                .sendEvent(SocketProperty.ROOM, response);
    }

    private void notifyRoomUsersOfferState(Room room) {
        room.getRoomUsers()
                .forEach(roomUser -> {
                    Offer offer = roomUser.getOffer();

                    OfferResponse response = OfferResponse
                            .builder()
                            .userId(roomUser.getId())
                            .isOffered(roomUser.getIsOffered())
                            .cardId(offer.getCardId())
                            .cardCount(offer.getCardCount())
                            .coin(offer.getCoin())
                            .build();

                    socketIOServer.getRoomOperations(room.getId().toString())
                            .sendEvent(SocketProperty.OFFER, response);
                });
    }

    private void makeRoomUserIsNotAcceptedState(Room room) {
        room.getRoomUsers()
                .forEach(roomUser -> {
                    roomUser.cancelAccept();
                    AcceptResponse response = new AcceptResponse(roomUser.getId(), roomUser.getIsAccepted());
                    socketIOServer.getRoomOperations(room.getId().toString())
                            .sendEvent(SocketProperty.ACCEPT, response);
                });
    }
}