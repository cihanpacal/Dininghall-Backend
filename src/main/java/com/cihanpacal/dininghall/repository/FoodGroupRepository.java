package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.FoodGroup;
import com.cihanpacal.dininghall.model.entity.ProductGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoodGroupRepository extends JpaRepository<FoodGroup,Long> {

    @Query("SELECT fg FROM FoodGroup fg WHERE fg.name LIKE %:filter%")
    Page<FoodGroup> findAll(@Param("filter") String filter, Pageable pageable);

    @Query("SELECT count(f) FROM Food f WHERE f.foodGroup.id= :foodGroupId")
    Long getCountOfFoodsByFoodGroup(@Param("foodGroupId") Long foodGroupId);

}
