package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:filter% " +
            "and (:productGroupId IS NULL or p.productGroup.id=:productGroupId)" +
            " and (:measurementUnitId IS NULL or p.measurementUnit.id=:measurementUnitId)")
    Page<Product> findAll(
            @Param("filter") String filter,
            @Param("productGroupId") Long productGroupId,
            @Param("measurementUnitId") Long measurementUnitId,
            Pageable pageable
    );

    @Query("Select count(s) from Stock s WHERE s.product.id=:productId")
    Long getCountOfStocksByProductId(@Param("productId") Long productId);

    @Query("SELECT count(fp) FROM FoodProduct fp WHERE fp.product.id= :productId")
    Long getCountOfFoodProductsByProductId(@Param("productId") Long productId);

    @Query("SELECT count(mp) FROM MenuProduct mp WHERE mp.product.id= :productId")
    Long getCountOfMenuProductsByProductId(@Param("productId") Long productId);




}
