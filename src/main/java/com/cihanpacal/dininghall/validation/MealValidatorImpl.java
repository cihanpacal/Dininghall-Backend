package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.Meal;
import com.cihanpacal.dininghall.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MealValidatorImpl implements  MealValidator {

    private final MealRepository mealRepository;

    @Override
    public Meal checkMealExist(Long id) {
        Optional<Meal> optionalMeal=mealRepository.findById(id);
        Meal meal=optionalMeal.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Öğün"});
        });
        return meal;
    }

    @Override
    public void checkAtLeastOneMealStockOutExists(Long id) {
        boolean flag=false;
        if(flag){
            throw new RuntimeException("deneme");
        }
    }


}
