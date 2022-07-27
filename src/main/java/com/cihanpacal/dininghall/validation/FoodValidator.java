package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.Food;

public interface FoodValidator {
    Food checkFoodExist(Long id);
    void checkAtLeastOneMenuFoodExists(Long id,String operationMessage);
}
