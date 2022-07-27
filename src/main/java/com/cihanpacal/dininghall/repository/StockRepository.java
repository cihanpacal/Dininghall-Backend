package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.Product;
import com.cihanpacal.dininghall.model.entity.Stock;
import com.cihanpacal.dininghall.model.response.StockResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock,Long> {

    @Query("SELECT s FROM Stock s WHERE" +
            "(:productId IS NULL or s.product.id=:productId)" +
            " and (:warehouseId IS NULL or s.warehouse.id=:warehouseId)")
    Page<Stock> findAll(
            @Param("productId") Long productId,
            @Param("warehouseId") Long warehouseId,
            Pageable pageable
    );

    Optional<Stock> findByProduct_IdAndWarehouse_Id(Long productId,Long warehouseId);



}
