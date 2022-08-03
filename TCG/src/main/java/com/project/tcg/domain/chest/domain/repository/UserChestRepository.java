package com.project.tcg.domain.chest.domain.repository;

import com.project.tcg.domain.chest.domain.UserChest;
import com.project.tcg.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserChestRepository extends CrudRepository<UserChest, User> {
}