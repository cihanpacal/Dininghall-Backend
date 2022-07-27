package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.FoodGroup;
import com.cihanpacal.dininghall.model.entity.MeasurementUnit;

public interface FoodGroupValidator {
    FoodGroup checkFoodGroupExist(Long id);
    void checkAtLeastOneFoodExists(Long id,String operationMessage);
}
