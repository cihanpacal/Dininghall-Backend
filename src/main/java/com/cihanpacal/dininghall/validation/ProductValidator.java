package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.Product;

public interface ProductValidator {
    Product checkProductExist(Long id);
    void checkAtLeastOneStockExists(Long id,String operationMessage);
    void checkAtLeastOneFoodProductExists(Long id,String operationMessage);
    void checkAtLeastOneBillDetailExists(Long id);
    void checkAtLeastOneMenuProductExists(Long id,String operationMessage);
}
