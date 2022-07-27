package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.Food;
import com.cihanpacal.dininghall.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {

    @Query("SELECT f FROM Food f  WHERE f.name LIKE %:filter% " +
            "and (:foodGroupId IS NULL or f.foodGroup.id=:foodGroupId)")
    Page<Food> findAll(
            @Param("filter") String filter,
            @Param("foodGroupId") Long foodGroupId,
            Pageable pageable
    );

    @Query("SELECT count(mf) FROM MenuFood mf WHERE mf.food.id= :foodId")
    Long getCountOfMenuFoodsByFoodId(@Param("foodId") Long foodId);



}
