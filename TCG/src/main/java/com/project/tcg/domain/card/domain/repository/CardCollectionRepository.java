package com.project.tcg.domain.card.domain.repository;

import com.project.tcg.domain.card.domain.CardCollection;
import org.springframework.data.repository.CrudRepository;

public interface CardCollectionRepository extends CrudRepository<CardCollection, Long> {
}