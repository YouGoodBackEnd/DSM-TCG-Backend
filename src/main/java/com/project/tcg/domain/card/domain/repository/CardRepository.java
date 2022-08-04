package com.project.tcg.domain.card.domain.repository;

import com.project.tcg.domain.card.domain.Card;
import com.project.tcg.domain.card.domain.enums.Grade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends CrudRepository<Card, Long> {

    List<Card> findByGrade(Grade grade);

    Optional<Card> findById(Long id);
}