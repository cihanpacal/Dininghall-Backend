package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.ProductGroup;
import com.cihanpacal.dininghall.util.OperationMessage;

public interface ProductGroupValidator {
    ProductGroup checkProductGroupExist(Long id);
    void checkAtLeastOneProductExists(Long id, String operationMessage);
}
