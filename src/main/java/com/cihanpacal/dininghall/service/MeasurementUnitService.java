package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.MeasurementUnitRequest;
import com.cihanpacal.dininghall.model.response.MeasurementUnitResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MeasurementUnitService {
    Page<MeasurementUnitResponse> getMeasurementUnits(Optional<String> filter, Pageable pageable);


    Long createMeasurementUnit(MeasurementUnitRequest measurementUnitRequest);

    MeasurementUnitResponse getMeasurementUnit(Long id);

    void updateMeasurementUnit(MeasurementUnitRequest measurementUnitRequest, Long id);

    void deleteMeasurementUnit(Long id);
}
