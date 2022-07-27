package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.BillProduct;
import com.cihanpacal.dininghall.model.entity.FoodProduct;

public interface BillProductValidator {
    BillProduct checkBillProductExist(Long id);
    void checkBillProductIsAlreadyExistByBillIdAndProductId(Long billId, Long productId);
}
