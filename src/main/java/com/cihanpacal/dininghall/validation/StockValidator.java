package com.cihanpacal.dininghall.validation;
import com.cihanpacal.dininghall.model.entity.Stock;

public interface StockValidator {
    Stock checkStockExist(Long id);
    Stock checkStockExistByProductIdAndWarehouseId(Long productId,Long warehouseId);
    void checkStockIsAlreadyExistByProductIdAndWarehouseId(Long productId, Long warehouseId);
}
