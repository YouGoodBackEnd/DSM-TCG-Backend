package com.project.tcg.domain.chat.domain.repository;

import com.project.tcg.domain.chat.domain.Room;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.project.tcg.domain.chat.domain.QRoomUser.roomUser;
import static com.project.tcg.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class CustomRoomUserRepositoryImpl implements CustomRoomUserRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public void cancelAllOfferIn(Room room) {
        queryFactory
                .update(roomUser)
                .set(roomUser.isOffered, false)
                .set(roomUser.offer.coin, (Integer) null)
                .set(roomUser.offer.cardCount, (Integer) null)
                .set(roomUser.offer.cardId, (Long) null)
                .where(roomUser.room.id.eq(room.getId()))
                .execute();
    }

    @Override
    public void cancelAllAccept(Room room) {
        queryFactory
                .update(roomUser)
                .set(roomUser.isAccepted, false)
                .where(roomUser.room.id.eq(room.getId()))
                .execute();
    }

    @Override
    public void deleteAllByAccountId(String accountId) {

        Long userId = queryFactory
                .select(user.id).from(user)
                .where(user.accountId.eq(accountId))
                .fetchOne();

        queryFactory
                .delete(roomUser)
                .where(roomUser.id.eq(userId))
                .execute();
    }
}