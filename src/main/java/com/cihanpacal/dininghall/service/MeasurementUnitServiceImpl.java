package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.MeasurementUnit;
import com.cihanpacal.dininghall.model.request.MeasurementUnitRequest;
import com.cihanpacal.dininghall.model.response.MeasurementUnitResponse;
import com.cihanpacal.dininghall.repository.MeasurementUnitRepository;
import com.cihanpacal.dininghall.util.OperationMessage;
import com.cihanpacal.dininghall.validation.MeasurementUnitValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasurementUnitServiceImpl implements MeasurementUnitService{

    private final MeasurementUnitRepository measurementUnitRepository;
    private final MeasurementUnitValidator measurementUnitValidator;
    private final ModelMapper modelMapper;

    @Override
    public Page<MeasurementUnitResponse> getMeasurementUnits(Optional<String> filter, Pageable pageable) {
        return measurementUnitRepository.findAll(filter.orElse(""),pageable).map((measurementUnit)->{
            return modelMapper.map(measurementUnit, MeasurementUnitResponse.class);
        });
    }

    @Override
    public Long createMeasurementUnit(MeasurementUnitRequest measurementUnitRequest) {

        MeasurementUnit measurementUnit=modelMapper.map(measurementUnitRequest,MeasurementUnit.class);
        measurementUnitRepository.save(measurementUnit);

        return measurementUnit.getId();
    }

    @Override
    public MeasurementUnitResponse getMeasurementUnit(Long id) {

        MeasurementUnit measurementUnit=measurementUnitValidator.checkMeasurementUnitExist(id);
        MeasurementUnitResponse measurementUnitResponse=modelMapper
                .map(measurementUnit,MeasurementUnitResponse.class);

        return measurementUnitResponse;
    }

    @Override
    public void updateMeasurementUnit(MeasurementUnitRequest measurementUnitRequest, Long id) {

        MeasurementUnit measurementUnit=measurementUnitValidator.checkMeasurementUnitExist(id);
        measurementUnit.setName(measurementUnitRequest.getName());
        measurementUnit.setShortName(measurementUnitRequest.getShortName());
        measurementUnit.setDescription(measurementUnitRequest.getDescription());
        measurementUnitRepository.save(measurementUnit);
    }

    @Override
    public void deleteMeasurementUnit(Long id) {
        MeasurementUnit measurementUnit=measurementUnitValidator.checkMeasurementUnitExist(id);
        measurementUnitValidator.checkAtLeastOneProductExists(measurementUnit.getId(), OperationMessage.DELETE);
        measurementUnitRepository.deleteById(measurementUnit.getId());
    }
}
