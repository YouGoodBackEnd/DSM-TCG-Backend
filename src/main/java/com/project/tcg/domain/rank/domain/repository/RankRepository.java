package com.project.tcg.domain.rank.domain.repository;

import com.project.tcg.domain.rank.domain.Rank;
import com.project.tcg.domain.rank.domain.RankId;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RankRepository extends CrudRepository<Rank, RankId> {

    @Procedure(procedureName = "SAVE_RANK")
    void saveRank();

    List<Rank> findTop100ByCreatedAtOrderByRanking(LocalDateTime date);
}