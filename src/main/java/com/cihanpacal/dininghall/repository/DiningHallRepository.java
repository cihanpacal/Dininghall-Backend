package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.DiningHall;
import com.cihanpacal.dininghall.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiningHallRepository extends JpaRepository<DiningHall,Long> {
    @Query("SELECT dh FROM DiningHall dh WHERE dh.name LIKE %:filter% and " +
            "(:warehouseId IS NULL or dh.warehouse.id=:warehouseId)")
    Page<DiningHall> findAll(
            @Param("filter") String filter,
            @Param("warehouseId") Long warehouseId,
            Pageable pageable
    );




    @Query("SELECT count(m) FROM Meal m WHERE m.diningHall.id= :diningHallId")
    Long getCountOfMealsByDiningHall(@Param("diningHallId") Long diningHallId);

}
