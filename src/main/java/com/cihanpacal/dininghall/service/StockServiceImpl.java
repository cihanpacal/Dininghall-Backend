package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.*;
import com.cihanpacal.dininghall.model.request.StockRequest;
import com.cihanpacal.dininghall.model.request.StockUpdateRequest;
import com.cihanpacal.dininghall.model.response.StockResponse;
import com.cihanpacal.dininghall.repository.StockRepository;
import com.cihanpacal.dininghall.validation.ProductValidator;
import com.cihanpacal.dininghall.validation.StockValidator;
import com.cihanpacal.dininghall.validation.WarehouseValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StockServiceImpl implements StockService{

    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;
    private final StockValidator stockValidator;
    private final ProductValidator productValidator;
    private final WarehouseValidator warehouseValidator;

    @Override
    public Page<StockResponse> getStocks(
            Optional<Long> optionalProductId,
            Optional<Long > optionalWarehouseId,
            Pageable pageable
    ) {


        Long productId=optionalProductId.orElse(null);
        Long warehouseId=optionalWarehouseId.orElse(null);

        return stockRepository.findAll(productId,warehouseId,pageable).map((stock)->{
            return modelMapper.map(stock, StockResponse.class);
        });

    }

    @Override
    public Long createStock(StockRequest stockRequest) {
        Product product=productValidator
                .checkProductExist(stockRequest.getProductId());
        Warehouse warehouse=warehouseValidator
                .checkWarehouseExist(stockRequest.getWarehouseId());

        //Veritabanında constraintler olduğu için bu validator aslında gereksiz
        //ancak hibernate hataları error controller tarafından yakalanmadığı için eklendi
        stockValidator
                .checkStockIsAlreadyExistByProductIdAndWarehouseId(product.getId(),warehouse.getId());

        Stock stock=new Stock();
        stock.setUnitPrice(0.0);
        stock.setQuantity(0.0);
        stock.setProduct(product);
        stock.setWarehouse(warehouse);
        stock.setStatusTime(new Date(0));

        stockRepository.save(stock);

        return stock.getId();
    }

    @Override
    public StockResponse getStock(Long id) {
        Stock stock=stockValidator.checkStockExist(id);
        StockResponse stockResponse=modelMapper.map(stock,StockResponse.class);
        return stockResponse;
    }

    @Override
    public void updateStock(@Valid StockUpdateRequest stockUpdateRequest, Long id) {
        Stock stock=stockValidator.checkStockExist(id);


        stock.setUnitPrice(stockUpdateRequest.getUnitPrice());
        stock.setQuantity(stockUpdateRequest.getQuantity());

        stockRepository.save(stock);
    }


}
