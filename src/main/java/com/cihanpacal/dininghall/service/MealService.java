package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.Food;
import com.cihanpacal.dininghall.model.request.FoodRequest;
import com.cihanpacal.dininghall.model.request.MealRequest;
import com.cihanpacal.dininghall.model.response.FoodResponse;
import com.cihanpacal.dininghall.model.response.MealProductResponse;
import com.cihanpacal.dininghall.model.response.MealResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface MealService {

    Page<MealResponse> getMeals(
            Optional<Long> optionalDiningHallId,
            Optional<Long> optionalMenuId,
            Optional<String> optionalStartDate,
            Optional<String> optionalEndDate,
            Optional<String> optionalStartTime,
            Optional<String> optionalEndTime,
            Pageable pageable
    );

    Long createMeal(MealRequest mealRequest);

    MealResponse getMeal(Long id);

    void updateMeal(MealRequest mealRequest, Long id);

    void deleteMeal(Long id);

    Page<MealProductResponse> getMealFoodContentProductsQuantityByMealId(
            Optional<String> filter,
            Long mealId,
            Optional<Long> optionalFoodId,
            Pageable pageable
    );
    Page<MealProductResponse>  getMealExternalProductsQuantityByMealId(
            Optional<String> filter,
            Long mealId,
            Pageable pageable
    );

    Page<FoodResponse> getMealFoodByMealId(
      Optional<String> filter,
      Long mealId,
      Pageable pageable
    );
}
