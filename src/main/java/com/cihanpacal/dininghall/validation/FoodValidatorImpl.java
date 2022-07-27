package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.EntityHasRelationshipException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.Food;
import com.cihanpacal.dininghall.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodValidatorImpl implements FoodValidator {

    private final FoodRepository foodRepository;

    @Override
    public Food checkFoodExist(Long id) {
        Optional<Food> optionalFood=foodRepository.findById(id);
        Food food=optionalFood.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Yemek"});
        });
        return food;
    }



    @Override
    public void checkAtLeastOneMenuFoodExists(Long id,String operationMessage) {
        long count=foodRepository.getCountOfMenuFoodsByFoodId(id);
        String name=foodRepository.findById(id).get().getName();
        if(count>0){
            throw new EntityHasRelationshipException(
                    "exception.EntityHasRelationshipException",
                    new Object[]{name,"adlı","yemek",operationMessage,"e",count,"menü içeriği (yemeği)"}
            );
        }
    }


}
