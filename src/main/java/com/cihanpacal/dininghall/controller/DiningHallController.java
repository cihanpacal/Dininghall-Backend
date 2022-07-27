package com.cihanpacal.dininghall.controller;


import com.cihanpacal.dininghall.model.request.DiningHallRequest;
import com.cihanpacal.dininghall.model.response.DiningHallResponse;
import com.cihanpacal.dininghall.service.DiningHallService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/dining-halls")
@RequiredArgsConstructor
public class DiningHallController {

    private final DiningHallService diningHallService;


    @GetMapping
    public ResponseEntity<Page<DiningHallResponse>> getDiningHalls(
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Long> warehouseId,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<DiningHallResponse> diningHallResponsePage=diningHallService.getDiningHalls(
                    filter,
                    warehouseId,
                    pageable
            );


        return ResponseEntity.ok(diningHallResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiningHallResponse> getDiningHall(@PathVariable Long id){
        DiningHallResponse diningHallResponse=diningHallService.getDiningHall(id);
        return ResponseEntity.ok(diningHallResponse);
    }

    @PostMapping
    public ResponseEntity<Long> createDiningHall(@RequestBody @Valid DiningHallRequest diningHallRequest){
        Long id=diningHallService.createDiningHall(diningHallRequest);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiningHall(@RequestBody @Valid DiningHallRequest diningHallRequest,@PathVariable Long id){

        diningHallService.updateDiningHall(diningHallRequest,id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiningHall(@PathVariable Long id){
        diningHallService.deleteDiningHall(id);
        return ResponseEntity.ok().build();
    }

}
