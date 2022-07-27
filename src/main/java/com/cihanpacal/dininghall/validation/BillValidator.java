package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.Bill;
import com.cihanpacal.dininghall.model.entity.Food;

public interface BillValidator {
    Bill checkBillExist(Long id);
}
