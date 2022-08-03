package com.project.tcg.domain.auth.domain.repository;

import com.project.tcg.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}