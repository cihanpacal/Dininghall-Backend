package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.StockTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface StockTransactionRepository extends JpaRepository<StockTransaction,Long> {

    @Query("SELECT st FROM StockTransaction st WHERE st.stock.product.name LIKE %:filter%  " +
            " and (:startDateTime IS NULL or st.transactionTime>=:startDateTime) " +
            "and (:endDateTime IS NULL or st.transactionTime<=:endDateTime)" +
            " and (:transactionType IS NULL or st.transactionType=:transactionType)" +
            " and (:productId IS NULL or st.stock.product.id=:productId)"+
            " and (:warehouseId IS NULL or st.stock.warehouse.id=:warehouseId)"
    )
    Page<StockTransaction> findAll(
            @Param("filter") String filter,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime,
            @Param("transactionType") Boolean transactionType,
            @Param("productId") Long productId,
            @Param("warehouseId") Long warehouseId,
            Pageable pageable
    );
}
