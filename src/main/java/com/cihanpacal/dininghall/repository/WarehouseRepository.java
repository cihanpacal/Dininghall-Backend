package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.ProductGroup;
import com.cihanpacal.dininghall.model.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {

    @Query("SELECT wh FROM Warehouse wh WHERE wh.name LIKE %:filter%")
    Page<Warehouse> findAll(@Param("filter") String filter, Pageable pageable);

    @Query("SELECT count(dh) FROM DiningHall dh WHERE dh.warehouse.id= :warehouseId")
    Long getCountOfDiningHallsByWarehouse(@Param("warehouseId") Long warehouseId);

    @Query("SELECT count(s) FROM Stock s WHERE s.warehouse.id= :warehouseId")
    long getCountOfStocksByWarehouse(@Param("warehouseId") Long warehouseId);

    @Query("SELECT count(b) FROM Bill b WHERE b.warehouse.id= :warehouseId")
    long getCountOfBillsByWarehouse(@Param("warehouseId") Long warehouseId);
}
