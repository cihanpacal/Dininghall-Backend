package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.EntityHasRelationshipException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.Warehouse;
import com.cihanpacal.dininghall.repository.StockRepository;
import com.cihanpacal.dininghall.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WarehouseValidatorImpl implements WarehouseValidator{

    private final WarehouseRepository warehouseRepository;
    private final StockRepository stockRepository;

    @Override
    public Warehouse checkWarehouseExist(Long id) {
        Optional<Warehouse> optional=warehouseRepository.findById(id);

        Warehouse productGroup=optional.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Depo"});
        });

        return  productGroup;
    }

    @Override
    public void checkAtLeastOneDiningHallExists(Long id,String operationMessage) {
        long count=warehouseRepository.getCountOfDiningHallsByWarehouse(id);
        String name=warehouseRepository.findById(id).get().getName();
        if(count>0){
            throw new EntityHasRelationshipException(
                    "exception.EntityHasRelationshipException",
                    new Object[]{name,"adlı","depo",operationMessage,"ya",count,"yemekhane"}
            );
        }
    }

    @Override
    public void checkAtLeastOneStockExists(Long id,String operationMessage) {
        long count=warehouseRepository.getCountOfStocksByWarehouse(id);
        String name=warehouseRepository.findById(id).get().getName();
        if(count>0){
            throw new EntityHasRelationshipException(
                    "exception.EntityHasRelationshipException",
                    new Object[]{name,"adlı","depo",operationMessage,"ya",count,"stok bilgisi"}
            );
        }
    }

    @Override
    public void checkAtLeastOneBillExists(Long id, String operationMessage) {
        long count=warehouseRepository.getCountOfBillsByWarehouse(id);
        String name=warehouseRepository.findById(id).get().getName();
        if(count>0){
            throw new EntityHasRelationshipException(
                    "exception.EntityHasRelationshipException",
                    new Object[]{name,"adlı","depo",operationMessage,"ya",count,"fatura"}
            );
        }
    }
}
