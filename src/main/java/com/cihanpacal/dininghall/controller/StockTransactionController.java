package com.cihanpacal.dininghall.controller;

import com.cihanpacal.dininghall.model.request.StockInTransactionRequest;
import com.cihanpacal.dininghall.model.request.StockTransactionRequest;
import com.cihanpacal.dininghall.model.response.StockTransactionResponse;
import com.cihanpacal.dininghall.service.StockTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/stock-transactions")
@RequiredArgsConstructor
public class StockTransactionController {

    private final StockTransactionService stockTransactionService;

    @GetMapping
    public ResponseEntity<Page<StockTransactionResponse>> getStockTransactions(
            @RequestParam Optional<String> filter,
            @RequestParam Optional<String> startTime,
            @RequestParam Optional<String> endTime,
            @RequestParam Optional<Boolean> transactionType,
            @RequestParam Optional<Long> productId,
            @RequestParam Optional<Long> warehouseId,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<StockTransactionResponse> stockTransactionResponsePage=stockTransactionService
                .getStockTransactions(
                        filter,
                        startTime,
                        endTime,
                        transactionType,
                        productId,
                        warehouseId,
                        pageable
                );

        return ResponseEntity.ok(stockTransactionResponsePage);

    }


    @PostMapping("/in")
    public ResponseEntity<Long> createStockInTransaction(
            @RequestBody @Valid StockInTransactionRequest stockInTransactionRequest
            ){
        Long id=stockTransactionService.createStockInTransaction(stockInTransactionRequest);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/out")
    public ResponseEntity<Long> createStockOutTransaction(
            @RequestBody @Valid StockTransactionRequest stockTransactionRequest
    ){
        Long id=stockTransactionService.createStockOutTransaction(stockTransactionRequest);
        return ResponseEntity.ok(id);
    }
}
