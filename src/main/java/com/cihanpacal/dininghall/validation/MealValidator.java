package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.Food;
import com.cihanpacal.dininghall.model.entity.Meal;

public interface MealValidator {
    Meal checkMealExist(Long id);
    void checkAtLeastOneMealStockOutExists(Long id);

}
