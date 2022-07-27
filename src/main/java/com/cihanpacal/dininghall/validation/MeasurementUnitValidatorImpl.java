package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.EntityHasRelationshipException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.MeasurementUnit;
import com.cihanpacal.dininghall.repository.MeasurementUnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MeasurementUnitValidatorImpl implements MeasurementUnitValidator{

    private final MeasurementUnitRepository measurementUnitRepository;

    @Override
    public MeasurementUnit checkMeasurementUnitExist(Long id) {
        Optional<MeasurementUnit> optional=measurementUnitRepository.findById(id);

        MeasurementUnit measurementUnit=optional.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Ölçü birimi"});
        });

        return  measurementUnit;
    }

    @Override
    public void checkAtLeastOneProductExists(Long id,String operationMessage) {
        long count=measurementUnitRepository.getCountOfProductsByMeasurementUnit(id);
        String name=measurementUnitRepository.findById(id).get().getName();
        if(count>0){
            throw new EntityHasRelationshipException(
                    "exception.EntityHasRelationshipException",
                    new Object[]{name,"adlı","ölçü birimi",operationMessage,"ne",count,"ürün"}
            );
        }
    }
}
