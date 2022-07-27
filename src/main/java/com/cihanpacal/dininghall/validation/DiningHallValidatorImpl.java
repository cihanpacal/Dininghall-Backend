package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.EntityHasRelationshipException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.DiningHall;
import com.cihanpacal.dininghall.repository.DiningHallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiningHallValidatorImpl implements DiningHallValidator{

    private final DiningHallRepository diningHallRepository;

    @Override
    public DiningHall checkDiningHallExist(Long id) {
        Optional<DiningHall> optionalDiningHall=diningHallRepository.findById(id);
        DiningHall diningHall=optionalDiningHall.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Yemekhane"});
        });
        return diningHall;
    }

    @Override
    public void checkAtLeastOneMealExists(Long id,String operationMessage) {
        long count=diningHallRepository.getCountOfMealsByDiningHall(id);
        String name=diningHallRepository.findById(id).get().getName();
        if(count>0){
            throw new EntityHasRelationshipException(
                    "exception.EntityHasRelationshipException",
                    new Object[]{name,"adlı","yemekhane",operationMessage,"ye",count,"öğün"}
            );
        }
    }
}
