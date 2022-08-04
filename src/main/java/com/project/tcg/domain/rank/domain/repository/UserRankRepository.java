package com.project.tcg.domain.rank.domain.repository;

import com.project.tcg.domain.rank.domain.UserRank;
import com.project.tcg.domain.rank.domain.UserRankId;
import org.springframework.data.repository.CrudRepository;

public interface UserRankRepository extends CrudRepository<UserRank, UserRankId> {

}