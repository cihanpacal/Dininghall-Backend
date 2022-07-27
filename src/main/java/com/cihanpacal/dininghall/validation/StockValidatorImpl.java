package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.ResourceAlreadyExistException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.Stock;
import com.cihanpacal.dininghall.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StockValidatorImpl implements StockValidator {

    private final StockRepository stockRepository;

    @Override
    public Stock checkStockExist(Long id) {
        Optional<Stock> optionalStock=stockRepository.findById(id);
        Stock stock=optionalStock.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Stok"});
        });
        return stock;
    }

    @Override
    public Stock checkStockExistByProductIdAndWarehouseId(Long productId, Long warehouseId) {
        Optional<Stock> optionalStock=stockRepository.findByProduct_IdAndWarehouse_Id(productId,warehouseId);
        Stock stock=optionalStock.orElseThrow(()->{
            throw new ResourceNotFoundException(
                    "exception.ResourceNotFoundExceptionByRelationshipEntity",
                    new Object[]{productId,"ürün",warehouseId,"depo","stok"}
            );
        });
        return stock;
    }

    @Override
    public void checkStockIsAlreadyExistByProductIdAndWarehouseId(Long productId, Long warehouseId) {
        Optional<Stock> optionalStock=stockRepository
                .findByProduct_IdAndWarehouse_Id(productId,warehouseId);
        optionalStock.ifPresent((stock)->{
            throw new ResourceAlreadyExistException(
                    "exception.ResourceAlreadyExistException",
                    new Object[]{"Stock bilgisi"}
            );
        });
    }


}
