package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.EntityHasRelationshipException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.FoodGroup;
import com.cihanpacal.dininghall.repository.FoodGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodGroupValidatorImpl implements FoodGroupValidator{

    private final FoodGroupRepository foodGroupRepository;

    @Override
    public FoodGroup checkFoodGroupExist(Long id) {
        Optional<FoodGroup> optional=foodGroupRepository.findById(id);

        FoodGroup foodGroup=optional.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Yemek Grubu"});
        });

        return  foodGroup;
    }

    @Override
    public void checkAtLeastOneFoodExists(Long id,String operationMessage) {
        long count=foodGroupRepository.getCountOfFoodsByFoodGroup(id);
        String name=foodGroupRepository.findById(id).get().getName();
        if(count>0){
            throw new EntityHasRelationshipException(
                    "exception.EntityHasRelationshipException",
                    new Object[]{name,"adlı","yemek grubu",operationMessage,"na",count,"yemek"}
            );
        }
    }
}
