package com.project.tcg;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.UserCard;
import com.project.tcg.domain.chat.domain.Room;
import com.project.tcg.domain.chat.domain.RoomUser;
import com.project.tcg.domain.trade.domain.Offer;
import com.project.tcg.domain.user.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.tcg.domain.card.domain.QCard.card;
import static com.project.tcg.domain.card.domain.QUserCard.userCard;
import static com.project.tcg.domain.chat.domain.QRoom.room;
import static com.project.tcg.domain.chat.domain.QRoomUser.roomUser;
import static com.project.tcg.domain.user.domain.QUser.user;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Transactional
public class QuerySpeedTest1 {

    @PersistenceUnit
    EntityManagerFactory emf;

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    Long roomId;

    Integer cardCount = 10 ;

    @BeforeEach
    void testEntity() {
        queryFactory = new JPAQueryFactory(em);

        Room room1 = Room
                .builder()
                .name("방제")
                .build();
        em.persist(room1);
        roomId = room1.getId();
        originalParticipate("rlaisqls");

        User findUser1 = queryFactory.selectFrom(user).where(user.accountId.eq("rlaisqls")).fetchOne();
        Card card1 = queryFactory.selectFrom(card).where(card.id.eq(1L)).fetchOne();
        User findUser2 = queryFactory.selectFrom(user).where(user.accountId.eq("qweqwe")).fetchOne();
        Card card2 = queryFactory.selectFrom(card).where(card.id.eq(2L)).fetchOne();
        for (int i = 0; i < cardCount; i++) {
            em.persist(UserCard.builder().user(findUser1).card(card1).build());
        }
        for (int i = 0; i < cardCount; i++) {
            em.persist(UserCard.builder().user(findUser2).card(card2).build());

        }
        em.flush();
        em.clear();
    }

    @Test
    public void fetchQuery() {
        em.clear();
        fetchParticipate("qweqwe");

        fetchOffer("rlaisqls",1L);
        fetchOffer("qweqwe",2L);

        fetchAccept("rlaisqls");
        fetchAccept("qweqwe");

        fetchTrade();
    }

    private void fetchParticipate(String accountId) {
        System.out.println("QuerySpeedTest.fetchParticipate");
        User findUser = queryFactory
                .select(user)
                .from(user)
                .where(user.accountId.eq(accountId))
                .fetchOne();

        Room findRoom = queryFactory
                .selectFrom(room)
                .where(room.id.eq(roomId))
                .join(room.roomUsers, roomUser).fetchJoin()
                .fetchOne();

        em.persist(RoomUser
                .builder()
                .id(findUser.getId())
                .room(findRoom)
                .user(findUser)
                .isOffered(false)
                .isAccepted(false)
                .build());

        queryFactory
                .insert(roomUser)
                .columns(roomUser.user, roomUser.room, roomUser.isAccepted, roomUser.isOffered)
                .values(findUser, findRoom, false, false);
        em.flush();
        em.clear();
    }

    private void fetchOffer(String accountId, Long offerCardId) {
        System.out.println("QuerySpeedTest.fetchOffer");
        User findUser = queryFactory
                .selectFrom(user)
                .where(user.accountId.eq(accountId))
                .join(user.roomUser, roomUser).fetchJoin()
                .join(roomUser.room, room).fetchJoin()
                .fetchOne();

        Offer offer = Offer.builder()
                .cardId(offerCardId)
                .cardCount(cardCount)
                .build();

        List<UserCard> userCards = queryFactory
                .selectFrom(userCard)
                .where(userCard.user.id.eq(findUser.getId())
                        .and(userCard.card.id.eq(offer.getCardId())))
                .limit(cardCount)
                .fetch();

        if (userCards.size() < offer.getCardCount()) fail();

        queryFactory
                .update(roomUser)
                .where(roomUser.id.eq(findUser.getId()))
                .set(roomUser.isOffered, true);

        em.flush();
        em.clear();
    }

    private void fetchAccept(String accountId) {
        System.out.println("QuerySpeedTest.fetchAccept");
        User findUser = queryFactory
                .selectFrom(user)
                .where(user.accountId.eq(accountId))
                .join(user.roomUser, roomUser).fetchJoin()
                .join(roomUser.room, room).fetchJoin()
                .join(room.roomUsers, roomUser).fetchJoin()
                .fetchOne();

        queryFactory.update(roomUser)
                .set(roomUser.isAccepted, true)
                .where(roomUser.id.eq(findUser.getId()));
        em.flush();
        em.clear();
    }

    private void fetchTrade() {
        System.out.println("QuerySpeedTest.fetchTrade");
        List<RoomUser> findRoomUsers2__ = queryFactory
                .selectFrom(roomUser)
                .where(roomUser.room.id.eq(roomId))
                .join(roomUser.user.userCardList, userCard)
                .fetch();

        RoomUser roomUser1 = findRoomUsers2__.get(0);
        RoomUser roomUser2 = findRoomUsers2__.get(1);
        User user1 = roomUser1.getUser();
        User user2 = roomUser2.getUser();

        for (UserCard userCard1 : user1.getUserCardList()) {
            em.persist(UserCard.builder()
                    .card(userCard1.getCard())
                    .user(user2)
                    .build());
            queryFactory.delete(userCard)
                    .where(userCard.id.eq(userCard1.getId()));
        }

        List<Long> longStream1 = user1.getUserCardList()
                .stream()
                .map(UserCard::getId)
                .collect(Collectors.toList());

        queryFactory.delete(userCard)
                .where(userCard.id.in(longStream1));

        for (UserCard userCard2 : user2.getUserCardList()) {
            em.persist(UserCard.builder()
                    .card(userCard2.getCard())
                    .user(user1)
                    .build());
        }

        List<Long> longStream2 = user2.getUserCardList()
                .stream()
                .map(UserCard::getId)
                .collect(Collectors.toList());

        queryFactory.delete(userCard)
                .where(userCard.id.in(longStream2));

        queryFactory
                .update(user)
                .set(user.cardCount.SSGradeCardCount, user.cardCount.SSGradeCardCount)
                .where(user.id.eq(user1.getId()));

        queryFactory
                .update(user)
                .set(user.cardCount.SGradeCardCount, user.cardCount.SGradeCardCount)
                .where(user.id.eq(user2.getId()));
    }

    @Test
    public void originalQuery() {
        em.clear();
        originalParticipate("qweqwe");

        originalOffer("rlaisqls", 1L);
        originalOffer("qweqwe", 2L);

        originalAccept("rlaisqls");
        originalAccept("qweqwe");

        originalTrade();
    }

    private void originalParticipate(String accountId) {
        User findUser = queryFactory
                .selectFrom(user).where(user.accountId.eq(accountId)).fetchOne();

        Room findRoom = queryFactory
                .selectFrom(room)
                .where(room.id.eq(roomId))
                .fetchOne();

        RoomUser findRoomUser = queryFactory
                .selectFrom(roomUser)
                .where(roomUser.id.eq(findUser.getId()))
                .fetchOne();

        if (findRoomUser != null) fail();

        em.persist(RoomUser.builder().room(findRoom).user(findUser).isAccepted(false).isOffered(false).build());

        List<RoomUser> findRoomUsers = queryFactory.selectFrom(roomUser)
                .where(roomUser.room.id.eq(findRoom.getId()))
                .fetch();

        queryFactory
                .insert(roomUser)
                .columns(roomUser.user, roomUser.room, roomUser.isAccepted, roomUser.isOffered)
                .values(findUser, findRoom, false, false);
        em.flush();
        em.clear();
    }

    private void originalOffer(String accountId, Long offerCardId) {
        User findUser = queryFactory.selectFrom(user).where(user.accountId.eq(accountId)).fetchOne();

        RoomUser findRoomUser = queryFactory.selectFrom(roomUser)
                .where(roomUser.id.eq(findUser.getId()))
                .fetchOne();

        Offer offer = Offer.builder()
                .cardId(offerCardId)
                .cardCount(cardCount)
                .build();

        List<UserCard> userCards = queryFactory
                .selectFrom(userCard)
                .where(userCard.user.id.eq(findUser.getId())
                        .and(userCard.card.id.eq(offer.getCardId())))
                .fetch();

        if (userCards.size() < offer.getCardCount()) fail();

        queryFactory
                .update(roomUser)
                .where(roomUser.id.eq(findUser.getId()))
                .set(roomUser.isOffered, true);

        em.flush();
        em.clear();
    }

    private void originalAccept(String accountId) {
        User findUser = queryFactory.selectFrom(user).where(user.accountId.eq(accountId)).fetchOne();

        RoomUser findRoomUser = queryFactory.selectFrom(roomUser)
                .where(roomUser.id.eq(findUser.getId()))
                .fetchOne();

        Room findRoom = queryFactory
                .selectFrom(room)
                .where(room.id.eq(roomId))
                .fetchOne();

        List<RoomUser> findRoomUsers__ = queryFactory.selectFrom(roomUser)
                .where(roomUser.room.id.eq(findRoom.getId()))
                .fetch();

        queryFactory.update(roomUser)
                .set(roomUser.isAccepted, true)
                .where(roomUser.id.eq(findUser.getId()));
        em.flush();
        em.clear();
    }

    private void originalTrade() {
        List<RoomUser> findRoomUsers2__ = queryFactory
                .selectFrom(roomUser)
                .where(roomUser.room.id.eq(roomId))
                .fetch();

        RoomUser roomUser1 = findRoomUsers2__.get(0);
        RoomUser roomUser2 = findRoomUsers2__.get(1);
        User user1 = roomUser1.getUser();
        User user2 = roomUser2.getUser();

        for (UserCard userCard1 : user1.getUserCardList()) {
            em.persist(UserCard.builder()
                    .card(userCard1.getCard())
                    .user(user2)
                    .build());
            queryFactory.delete(userCard)
                    .where(userCard.id.eq(userCard1.getId()));
        }

        for (UserCard userCard2 : user2.getUserCardList()) {
            em.persist(UserCard.builder()
                    .card(userCard2.getCard())
                    .user(user1)
                    .build());
            queryFactory.delete(userCard)
                    .where(userCard.id.eq(userCard2.getId()));
        }

        queryFactory
                .update(user)
                .set(user.cardCount.SSGradeCardCount, user.cardCount.SSGradeCardCount)
                .where(user.id.eq(user1.getId()));

        queryFactory
                .update(user)
                .set(user.cardCount.SGradeCardCount, user.cardCount.SGradeCardCount)
                .where(user.id.eq(user2.getId()));
    }

}