package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {

    public Optional<VerificationToken> findByToken(String token);

    void deleteByUser_Id(Long userId);
}
