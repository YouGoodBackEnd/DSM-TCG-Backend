package com.project.tcg.domain.user.domain.repository;

import com.project.tcg.domain.chat.domain.repository.vo.QUserRoomVO;
import com.project.tcg.domain.chat.domain.repository.vo.UserRoomVO;
import com.project.tcg.domain.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.project.tcg.domain.chat.domain.QRoom.room;
import static com.project.tcg.domain.chat.domain.QRoomUser.roomUser;
import static com.project.tcg.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<User> findUserNotJoined(String accountId) {
        return Optional.ofNullable(queryFactory
                .select(user)
                .from(user)
                .leftJoin(user.roomUser, roomUser)
                .where(user.accountId.eq(accountId)
                        , user.roomUser.id.isNull())
                .fetchOne());
    }

    @Override
    public Optional<UserRoomVO> findUserAndRoomId(String accountId) {
        return Optional.ofNullable(queryFactory
                .select(new QUserRoomVO(
                        room.id,
                        user.id,
                        user.profileImageUrl,
                        user.name))
                .from(user)
                .innerJoin(user.roomUser, roomUser)
                .innerJoin(roomUser.room, room)
                .on(room.id.eq(user.roomUser.room.id))
                .where(user.accountId.eq(accountId))
                .fetchOne());
    }

    @Override
    public Optional<User> findUserAndFetchRoom(String accountId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(user)
                .distinct()
                .innerJoin(user.roomUser, roomUser).fetchJoin()
                .innerJoin(roomUser.room, room).fetchJoin()
                .innerJoin(room.roomUsers, roomUser).fetchJoin()
                .where(user.accountId.eq(accountId),
                        user.roomUser.id.isNotNull())
                .fetchOne());
    }

}