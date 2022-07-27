package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.ProductGroup;
import com.cihanpacal.dininghall.model.entity.Warehouse;

public interface WarehouseValidator {
    Warehouse checkWarehouseExist(Long id);
    void checkAtLeastOneDiningHallExists(Long id,String operationMessage);
    void checkAtLeastOneStockExists(Long id,String operationMessage);
    void checkAtLeastOneBillExists(Long id,String operationMessage);
}
