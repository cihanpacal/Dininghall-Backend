package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.Food;
import com.cihanpacal.dininghall.model.entity.Menu;

public interface MenuValidator {
    Menu checkMenuExist(Long id);
    void checkMenuMustHasAtLeastOneMenuFood(Long id);
}
