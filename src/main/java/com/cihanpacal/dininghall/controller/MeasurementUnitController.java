package com.cihanpacal.dininghall.controller;

import com.cihanpacal.dininghall.model.request.MeasurementUnitRequest;
import com.cihanpacal.dininghall.model.response.MeasurementUnitResponse;
import com.cihanpacal.dininghall.service.MeasurementUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/measurement-units")
@RequiredArgsConstructor
public class MeasurementUnitController {

    private final MeasurementUnitService measurementUnitService;

    @GetMapping
    public ResponseEntity<Page<MeasurementUnitResponse>> getMeasurementUnits(
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<MeasurementUnitResponse> measurementUnitResponsePage=measurementUnitService
                .getMeasurementUnits(filter,pageable);


        return  ResponseEntity.ok(measurementUnitResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasurementUnitResponse> getMeasurementUnit(@PathVariable Long id){
        MeasurementUnitResponse measurementUnitResponse=measurementUnitService.getMeasurementUnit(id);
        return ResponseEntity.ok(measurementUnitResponse);
    }

    @PostMapping
    public ResponseEntity<Long> createMeasurementUnit(@RequestBody @Valid MeasurementUnitRequest measurementUnitRequest){
        Long id=measurementUnitService.createMeasurementUnit(measurementUnitRequest);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMeasurementUnit(
            @RequestBody @Valid MeasurementUnitRequest measurementUnitRequest,
            @PathVariable Long id
    ){

        measurementUnitService.updateMeasurementUnit(measurementUnitRequest,id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeasurementUnit(@PathVariable Long id){

        measurementUnitService.deleteMeasurementUnit(id);

        return ResponseEntity.ok().build();
    }
}
