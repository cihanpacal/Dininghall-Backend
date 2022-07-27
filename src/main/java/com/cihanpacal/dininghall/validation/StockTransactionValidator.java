package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.Stock;
import com.cihanpacal.dininghall.model.entity.StockTransaction;

public interface StockTransactionValidator {
    StockTransaction checkStockTransactionExist(Long id);
}
