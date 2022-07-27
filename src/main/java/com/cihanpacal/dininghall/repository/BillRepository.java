package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.Bill;
import com.cihanpacal.dininghall.model.entity.StockTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface BillRepository extends JpaRepository<Bill,Long> {

    @Query("SELECT b FROM Bill b WHERE  " +
            " (:startDateTime IS NULL or b.time>=:startDateTime) " +
            "and (:endDateTime IS NULL or b.time<=:endDateTime)" +
            " and (:warehouseId IS NULL or b.warehouse.id=:warehouseId)"
    )
    Page<Bill> findAll(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("warehouseId") Long warehouseId,
            Pageable pageable
    );
}
