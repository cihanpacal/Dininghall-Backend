package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken,Long> {
    Optional<AuthenticationToken> findByToken(String token);
    public void deleteByUser_Id(Long userId);
}
