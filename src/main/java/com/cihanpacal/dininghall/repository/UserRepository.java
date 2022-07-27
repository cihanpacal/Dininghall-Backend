package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.FoodGroup;
import com.cihanpacal.dininghall.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.stream.DoubleStream;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);


    @Query("SELECT u FROM User u WHERE " +
            "u.email LIKE %:filter% or u.firstName LIKE %:filter% or u.lastName LIKE %:filter% ")
    Page<User> findAll(@Param("filter") String filter, Pageable pageable);
}
