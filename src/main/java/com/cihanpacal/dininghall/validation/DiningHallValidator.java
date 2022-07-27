package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.DiningHall;
import com.cihanpacal.dininghall.model.entity.Product;

public interface DiningHallValidator {
    DiningHall checkDiningHallExist(Long id);
    void checkAtLeastOneMealExists(Long id,String operationMessage);
}
