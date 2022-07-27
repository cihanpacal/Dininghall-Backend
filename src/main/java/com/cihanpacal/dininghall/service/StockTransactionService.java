package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.StockInTransactionRequest;
import com.cihanpacal.dininghall.model.request.StockTransactionRequest;
import com.cihanpacal.dininghall.model.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StockTransactionService {

    Page<StockTransactionResponse> getStockTransactions(
            Optional<String>  filter,
            Optional<String> optionalStartDateTime,
            Optional<String> optionalEndDateTime,
            Optional<Boolean> optionalTransactionType,
            Optional<Long> optionalProductId,
            Optional<Long> optionalWarehouseId,
            Pageable pageable
    );

    Long createStockInTransaction(StockInTransactionRequest stockInTransactionRequest);

    Long createStockOutTransaction(StockTransactionRequest stockTransactionRequest);

    StockTransactionResponse getStockTransaction(Long id);


}
