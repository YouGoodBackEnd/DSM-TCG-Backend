package com.project.tcg.domain.card.domain.repository;

import com.project.tcg.domain.card.domain.CardCollection;
import com.project.tcg.domain.card.domain.CardCollectionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardCollectionRepository extends CrudRepository<CardCollection, CardCollectionId> {
    List<CardCollection> findByUserId(Long userId);

}