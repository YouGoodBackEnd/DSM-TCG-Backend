package com.project.tcg.domain.card.domain.repository;

import com.project.tcg.domain.card.domain.UserCard;
import com.project.tcg.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CardCollectionRepository extends CrudRepository<UserCard, Long> {
    List<UserCard> findByUserId(Long userId);
    Optional<UserCard> findByUserAndCard_id(User user, Long cardId);

}