package com.cihanpacal.dininghall.controller;


import com.cihanpacal.dininghall.model.request.BillProductRequest;
import com.cihanpacal.dininghall.model.request.BillProductUpdateRequest;
import com.cihanpacal.dininghall.model.response.BillProductResponse;
import com.cihanpacal.dininghall.service.BillProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/bill-products")
@RequiredArgsConstructor
public class BillProductController {

    private final BillProductService billProductService;

    @GetMapping
    public ResponseEntity<Page<BillProductResponse>> getBillProductsByBillId(
            @RequestParam(required = true) Long billId,
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<BillProductResponse> billProductResponsePage=billProductService.getBillProductsByBillId(
                filter,
                billId,
                pageable
        );

        return ResponseEntity.ok(billProductResponsePage);
    }

    @PostMapping
    public ResponseEntity<Long> createBillProduct(
            @RequestBody @Valid BillProductRequest billProductRequest
    ){
        Long id=billProductService.createBillProduct(billProductRequest);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBillProduct(
            @RequestBody @Valid BillProductUpdateRequest billProductUpdateRequest,
            @PathVariable Long id
    ){

        billProductService.updateBillProduct(billProductUpdateRequest,id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBillProduct(@PathVariable Long id){
        billProductService.deleteBillProduct(id);

        return ResponseEntity.ok().build();
    }
}
