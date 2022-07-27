package com.cihanpacal.dininghall.controller;


import com.cihanpacal.dininghall.model.request.StockRequest;
import com.cihanpacal.dininghall.model.response.StockResponse;
import com.cihanpacal.dininghall.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public ResponseEntity<Page<StockResponse>> getStocks(
            @RequestParam Optional<Long> productId,
            @RequestParam Optional<Long> warehouseId,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){
        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<StockResponse> stockResponsePage=stockService.getStocks(
                productId,
                warehouseId,
                pageable
        );

        return ResponseEntity.ok(stockResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponse> getStock(@PathVariable Long id){
        StockResponse stockResponse=stockService.getStock(id);
        return ResponseEntity.ok(stockResponse);
    }

    @PostMapping
    public ResponseEntity<Long> createStock(@RequestBody @Valid StockRequest stockRequest){
        Long id=stockService.createStock(stockRequest);
        return ResponseEntity.ok(id);
    }



}
