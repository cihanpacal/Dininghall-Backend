package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.Stock;
import com.cihanpacal.dininghall.model.entity.StockInTransaction;
import com.cihanpacal.dininghall.model.entity.StockOutTransaction;
import com.cihanpacal.dininghall.model.entity.StockTransaction;
import com.cihanpacal.dininghall.model.request.StockInTransactionRequest;
import com.cihanpacal.dininghall.model.request.StockTransactionRequest;
import com.cihanpacal.dininghall.model.request.StockUpdateRequest;
import com.cihanpacal.dininghall.model.response.StockTransactionResponse;
import com.cihanpacal.dininghall.repository.StockTransactionRepository;
import com.cihanpacal.dininghall.validation.ProductValidator;
import com.cihanpacal.dininghall.validation.StockTransactionValidator;
import com.cihanpacal.dininghall.validation.StockValidator;
import com.cihanpacal.dininghall.validation.WarehouseValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StockTransactionServiceImpl implements StockTransactionService {

    private final ModelMapper modelMapper;
    private final StockTransactionRepository stockTransactionRepository;
    private final StockTransactionValidator stockTransactionValidator;
    private final ProductValidator productValidator;
    private final WarehouseValidator warehouseValidator;
    private final StockValidator stockValidator;
    private final StockService stockService;

    @Override
    public Page<StockTransactionResponse> getStockTransactions(
            Optional<String> filter,
            Optional<String> optionalStartDateTime,
            Optional<String> optionalEndDateTime,
            Optional<Boolean> optionalTransactionType,
            Optional<Long> optionalProductId,
            Optional<Long> optionalWarehouseId,
            Pageable pageable
    ) {

        Long productId=optionalProductId.orElse(null);
        Long warehouseId=optionalWarehouseId.orElse(null);
        LocalDateTime startDateTime= LocalDateTime.parse(optionalStartDateTime.orElse("1970-01-01T"+LocalTime.MIN.toString()));
        LocalDateTime endDateTime=LocalDateTime.parse(optionalEndDateTime.orElse("2100-01-01T"+LocalTime.MAX.toString()));
        Boolean transactionType=optionalTransactionType.orElse(null);

        return stockTransactionRepository
                .findAll(
                        filter.orElse(""),
                        startDateTime,
                        endDateTime,
                        transactionType,
                        productId,
                        warehouseId,
                        pageable

                ).map((stockTransaction)->{
            return modelMapper.map(stockTransaction, StockTransactionResponse.class);
        });
    }

    @Override
    public Long createStockInTransaction(StockInTransactionRequest stockInTransactionRequest) {

        productValidator.checkProductExist(stockInTransactionRequest.getProductId());
        warehouseValidator.checkWarehouseExist(stockInTransactionRequest.getWarehouseId());
        Stock stock=stockValidator.checkStockExistByProductIdAndWarehouseId(
                stockInTransactionRequest.getProductId(),
                stockInTransactionRequest.getWarehouseId()
        );


        Double newStockQuantity=stock.getQuantity()+stockInTransactionRequest.getQuantity();
        Double newUnitPrice=(stock.getQuantity()*stock.getUnitPrice()
                +stockInTransactionRequest.getQuantity() *stockInTransactionRequest.getUnitPrice())/newStockQuantity;

        StockUpdateRequest stockUpdateRequest=new StockUpdateRequest();
        stockUpdateRequest.setQuantity(newStockQuantity);
        stockUpdateRequest.setUnitPrice(newUnitPrice);
        stockService.updateStock(stockUpdateRequest,stock.getId());

        StockInTransaction stockInTransaction=new StockInTransaction();

        stockInTransaction.setStock(stock);
        stockInTransaction.setTransactionTime(LocalDateTime.now());
        stockInTransaction.setQuantity(stockInTransactionRequest.getQuantity());
        stockInTransaction.setUnitPrice(stockInTransactionRequest.getUnitPrice());
        stockInTransaction.setDescription(stockInTransactionRequest.getDescription());

        return stockTransactionRepository.save(stockInTransaction).getId();
    }

    @Override
    public Long createStockOutTransaction(StockTransactionRequest stockTransactionRequest) {

        productValidator.checkProductExist(stockTransactionRequest.getProductId());
        warehouseValidator.checkWarehouseExist(stockTransactionRequest.getWarehouseId());
        Stock stock=stockValidator.checkStockExistByProductIdAndWarehouseId(
                stockTransactionRequest.getProductId(),
                stockTransactionRequest.getWarehouseId()
        );

        Double newStockQuantity=stock.getQuantity()-stockTransactionRequest.getQuantity();

        StockUpdateRequest stockUpdateRequest=new StockUpdateRequest();
        stockUpdateRequest.setQuantity(newStockQuantity);
        stockUpdateRequest.setUnitPrice(newStockQuantity==0? 0 : stock.getUnitPrice());
        stockService.updateStock(stockUpdateRequest,stock.getId());

        StockOutTransaction stockOutTransaction=new StockOutTransaction();

        stockOutTransaction.setStock(stock);
        stockOutTransaction.setTransactionTime(LocalDateTime.now());
        stockOutTransaction.setQuantity(stockTransactionRequest.getQuantity());
        stockOutTransaction.setUnitPrice(stock.getUnitPrice());
        stockOutTransaction.setDescription(stockTransactionRequest.getDescription());

        return stockTransactionRepository.save(stockOutTransaction).getId();
    }


    @Override
    public StockTransactionResponse getStockTransaction(Long id) {
        StockTransaction stockTransaction=stockTransactionValidator.checkStockTransactionExist(id);
        StockTransactionResponse stockTransactionResponse=modelMapper
                .map(stockTransaction,StockTransactionResponse.class);
        return stockTransactionResponse;
    }




}
