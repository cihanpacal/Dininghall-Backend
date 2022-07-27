package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {
    public Optional<PasswordResetToken> findByToken(String token);
}
