package com.project.tcg.domain.card.domain.repository;

import com.project.tcg.domain.card.domain.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {

    List<Card> findBy();
}