package com.cihanpacal.dininghall.controller;


import com.cihanpacal.dininghall.model.request.WarehouseRequest;
import com.cihanpacal.dininghall.model.response.WarehouseResponse;
import com.cihanpacal.dininghall.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<Page<WarehouseResponse>> getWarehouses(
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<WarehouseResponse> warehouseResponsePage=warehouseService
                .getWarehouses(filter,pageable);


        return  ResponseEntity.ok(warehouseResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponse> getWarehouse(@PathVariable Long id){
        WarehouseResponse warehouseResponse=warehouseService.getWarehouse(id);
        return ResponseEntity.ok(warehouseResponse);
    }

    @PostMapping
    public ResponseEntity<Long> createWarehouse(@RequestBody @Valid WarehouseRequest warehouseRequest){
        Long id=warehouseService.createWarehouse(warehouseRequest);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWarehouse(
            @RequestBody @Valid WarehouseRequest warehouseRequest,
            @PathVariable Long id
    ){

        warehouseService.updateWarehouse(warehouseRequest,id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable Long id){

        warehouseService.deleteWarehouse(id);

        return ResponseEntity.ok().build();
    }
}
