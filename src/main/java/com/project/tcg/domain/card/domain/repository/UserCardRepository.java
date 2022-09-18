package com.project.tcg.domain.card.domain.repository;

import com.project.tcg.domain.card.domain.UserCard;
import com.project.tcg.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserCardRepository extends CrudRepository<UserCard, Long> {

    Optional<UserCard> findFirstByUserAndCard_id(User user, Long cardId);
    List<UserCard> findByCard_IdAndUser(Long cardId, User user);
    List<UserCard> findByUser(User user);
}