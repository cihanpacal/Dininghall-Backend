package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.FoodProduct;

public interface FoodProductValidator {
    FoodProduct checkFoodProductExist(Long id);
    void checkFoodProductIsAlreadyExistByFoodIdAndProductId(Long foodId, Long productId);
    void checkAtLeastOneMealExists(Long id,String operationMessage);
}
