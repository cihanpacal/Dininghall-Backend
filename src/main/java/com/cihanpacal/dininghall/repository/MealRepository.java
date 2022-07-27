package com.cihanpacal.dininghall.repository;

import com.cihanpacal.dininghall.model.entity.Food;
import com.cihanpacal.dininghall.model.entity.Meal;
import com.cihanpacal.dininghall.model.response.MealProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

public interface MealRepository extends JpaRepository<Meal,Long> {

    @Query("SELECT m FROM Meal m WHERE (:startDate IS NULL or m.date>=:startDate) " +
            "and (:endDate IS NULL or m.date<=:endDate)" +
            " and (:startTime IS NULL or m.time>=:startTime)"+
            " and (:endTime IS NULL or m.time<=:endTime)"+
            " and (:diningHallId IS NULL or m.diningHall.id=:diningHallId)" +
            " and (:menuId IS NULL or m.menu.id=:menuId)")
    Page<Meal> findAll(
            @Param("diningHallId") Long diningHallId,
            @Param("menuId") Long menuId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("startTime")LocalTime startTime,
            @Param("endTime") LocalTime endTime,
            Pageable pageable
            );

    //bu ve aşağıdaki sorgu union ile birleştirilmesi gerekirdi ama jpa ve hibernate union yok
    @Query("SELECT new com.cihanpacal.dininghall.model.response.MealProductResponse((sum(fp.quantity) * m.numberOfPeople),fp.product.id,fp.product.name,fp.product.measurementUnit.shortName)    from " +
            "FoodProduct fp inner join fp.food.menuFoods mf  inner join mf.menu.meals m " +
            "where m.id=:mealId " +
            "and (:foodId IS NULL or mf.food.id=:foodId) " +
            "and fp.product.name LIKE %:filter% " +
            "group by fp.product.id")
    Page<MealProductResponse> findMealFoodContentProductsQuantityByMealId(
            @Param("filter") String filter,
            @Param("mealId") Long mealId,
            @Param("foodId") Long foodId,
            Pageable pageable
    );



    @Query("SELECT new com.cihanpacal.dininghall.model.response.MealProductResponse((sum(mp.quantity) * m.numberOfPeople),mp.product.id,mp.product.name,mp.product.measurementUnit.shortName)    from " +
            "MenuProduct mp inner join mp.menu.meals m " +
            "where m.id=:mealId " +
            "and mp.product.name LIKE %:filter% " +
            "group by mp.product.id")
    Page<MealProductResponse> findMealExternalProductsQuantityByMealId(
            @Param("filter") String filter,
            @Param("mealId") Long mealId,
            Pageable pageable
    );

    @Query("SELECT f from Food f inner join f.menuFoods mf inner join mf.menu.meals m" +
            " where m.id= :mealId and f.name like %:filter%")
    Page<Food> findMealFoodByMealId(
            @Param("filter") String filter,
            @Param("mealId") Long mealId,
            Pageable pageable
    );


}
