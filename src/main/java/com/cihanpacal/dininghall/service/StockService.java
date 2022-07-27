package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.ProductRequest;
import com.cihanpacal.dininghall.model.request.StockRequest;
import com.cihanpacal.dininghall.model.request.StockUpdateRequest;
import com.cihanpacal.dininghall.model.response.ProductResponse;
import com.cihanpacal.dininghall.model.response.StockResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StockService {

    Page<StockResponse> getStocks(
            Optional<Long> optionalProductId,
            Optional<Long > optionalWarehouseId,
            Pageable pageable
    );



    Long createStock(StockRequest stockRequest);

    StockResponse getStock(Long id);

    void updateStock(StockUpdateRequest stockUpdateRequest, Long id);


}
