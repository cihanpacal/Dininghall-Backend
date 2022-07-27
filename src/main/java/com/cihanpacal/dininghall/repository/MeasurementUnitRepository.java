package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.MeasurementUnit;
import com.cihanpacal.dininghall.model.entity.ProductGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MeasurementUnitRepository extends JpaRepository<MeasurementUnit,Long> {
    @Query("SELECT mu FROM MeasurementUnit mu WHERE mu.name LIKE %:filter%")
    Page<MeasurementUnit> findAll(@Param("filter") String filter, Pageable pageable);

    @Query("SELECT count(p) FROM Product p WHERE p.measurementUnit.id= :measurementUnitId")
    long getCountOfProductsByMeasurementUnit(@Param("measurementUnitId") Long measurementUnitId);

}
