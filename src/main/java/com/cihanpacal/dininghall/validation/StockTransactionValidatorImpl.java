package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.StockTransaction;
import com.cihanpacal.dininghall.repository.StockTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StockTransactionValidatorImpl implements StockTransactionValidator {

    private final StockTransactionRepository stockTransactionRepository;

    @Override
    public StockTransaction checkStockTransactionExist(Long id) {
        Optional<StockTransaction> optionalStockTransaction=stockTransactionRepository.findById(id);
        StockTransaction stockTransaction=optionalStockTransaction.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Stok Hareketi"});
        });
        return stockTransaction;
    }


}
