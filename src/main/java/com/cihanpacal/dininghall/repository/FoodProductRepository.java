package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.FoodProduct;
import com.cihanpacal.dininghall.model.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FoodProductRepository extends JpaRepository<FoodProduct,Long> {

    @Query("SELECT fp FROM FoodProduct fp WHERE fp.product.name LIKE %:filter% and fp.food.id=:foodId")
    Page<FoodProduct> findByFoodId(
            @Param("filter") String filter,
            @Param("foodId") Long foodId,
            Pageable pageable
    );

    Optional<FoodProduct> findByFood_IdAndProduct_Id(Long foodId,Long productId);

    void deleteByFood_Id(Long foodId);

    @Query("SELECT count(distinct m) FROM Meal m inner join m.menu.menuFoods mf inner join mf.food.foodProducts fp " +
            "Where fp.id=:foodProductId")
    long getCountOfMealsByFoodProduct(@Param("foodProductId") Long foodProductId);



}
