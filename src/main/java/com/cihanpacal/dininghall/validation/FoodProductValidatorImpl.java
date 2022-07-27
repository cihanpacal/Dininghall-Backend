package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.EntityHasRelationshipException;
import com.cihanpacal.dininghall.error.ResourceAlreadyExistException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.FoodProduct;
import com.cihanpacal.dininghall.repository.FoodProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodProductValidatorImpl implements FoodProductValidator{

    private final FoodProductRepository foodProductRepository;

    @Override
    public FoodProduct checkFoodProductExist(Long id) {
        Optional<FoodProduct> optionalFoodProduct=foodProductRepository.findById(id);
        FoodProduct foodProduct=optionalFoodProduct.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Yemek Ürünü"});
        });
        return foodProduct;
    }

    @Override
    public void checkFoodProductIsAlreadyExistByFoodIdAndProductId(Long foodId, Long productId) {
        Optional<FoodProduct> optionalFoodProduct=foodProductRepository
                .findByFood_IdAndProduct_Id(foodId,productId);
        optionalFoodProduct.ifPresent((foodProduct)->{
            throw new ResourceAlreadyExistException(
                    "exception.ResourceAlreadyExistException",
                    new Object[]{"Yemek İçeriği"}
                    );
        });
    }

    @Override
    public void checkAtLeastOneMealExists(Long id, String operationMessage) {
        long count=foodProductRepository.getCountOfMealsByFoodProduct(id);
        String foodName=foodProductRepository.findById(id).get().getFood().getName();
        if(count>0){
            throw new EntityHasRelationshipException(
                    "exception.EntityHasRelationshipException",
                    new Object[]{foodName,"adlı","yemek içeriği",operationMessage,"ne",count,"öğün"}
            );
        }
    }
}
