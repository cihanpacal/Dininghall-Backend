package com.cihanpacal.dininghall.controller;

import com.cihanpacal.dininghall.model.request.BillRequest;
import com.cihanpacal.dininghall.model.response.BillResponse;
import com.cihanpacal.dininghall.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping
    public ResponseEntity<Page<BillResponse>> getBills(
            @RequestParam Optional<String> startTime,
            @RequestParam Optional<String> endTime,
            @RequestParam Optional<Long> warehouseId,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<BillResponse> billResponsePage=billService
                .getBills(
                        startTime,
                        endTime,
                        warehouseId,
                        pageable
                );

        return ResponseEntity.ok(billResponsePage);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BillResponse> getBill(@PathVariable Long id){
        BillResponse billResponse=billService.getBill(id);
        return ResponseEntity.ok(billResponse);
    }

    @PostMapping
    public ResponseEntity<Long> createBill(@RequestBody @Valid BillRequest billRequest){
        Long id=billService.createBill(billRequest);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBill(@RequestBody @Valid BillRequest billRequest,@PathVariable Long id){

        billService.updateBill(billRequest,id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBill(@PathVariable Long id){
        billService.deleteBill(id);

        return ResponseEntity.ok().build();
    }
}
