package com.project.tcg.domain.chest.domain.repository;

import com.project.tcg.domain.chest.domain.UserChest;
import org.springframework.data.repository.CrudRepository;

public interface UserChestRepository extends CrudRepository<UserChest, Long> {
}