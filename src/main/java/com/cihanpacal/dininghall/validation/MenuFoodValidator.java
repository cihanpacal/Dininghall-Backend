package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.FoodProduct;
import com.cihanpacal.dininghall.model.entity.MenuFood;

public interface MenuFoodValidator {
    MenuFood checkMenuFoodExist(Long id);
    void checkMenuFoodIsAlreadyExistByMenuIdAndFoodId(Long menuId, Long foodId);
    void checkFoodMustHasAtLeastOneFoodProduct(Long id);
}
