package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.MeasurementUnit;
import com.cihanpacal.dininghall.model.entity.ProductGroup;

public interface MeasurementUnitValidator {

    MeasurementUnit checkMeasurementUnitExist(Long id);
    void checkAtLeastOneProductExists(Long id,String operationMessage);
}
