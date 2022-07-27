package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.FoodProduct;
import com.cihanpacal.dininghall.model.entity.MenuProduct;

public interface MenuProductValidator {
    MenuProduct checkMenuProductExist(Long id);
    void checkMenuProductIsAlreadyExistByMenuIdAndProductId(Long menuId, Long productId);
}
