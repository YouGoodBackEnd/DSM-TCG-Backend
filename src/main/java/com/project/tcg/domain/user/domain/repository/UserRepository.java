package com.project.tcg.domain.user.domain.repository;

import com.project.tcg.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>, CustomUserRepository {
    Optional<User> findByAccountId(String accountId);
}