package com.cihanpacal.dininghall.controller;

import com.cihanpacal.dininghall.model.request.FoodRequest;
import com.cihanpacal.dininghall.model.response.FoodResponse;
import com.cihanpacal.dininghall.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<Page<FoodResponse>> getFoods(
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Long> foodGroupId,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<FoodResponse> foodResponsePage=foodService.getFoods(
                filter,
                foodGroupId,
                pageable
        );

        return ResponseEntity.ok(foodResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponse> getFood(@PathVariable Long id){
        FoodResponse foodResponse=foodService.getFood(id);
        return ResponseEntity.ok(foodResponse);
    }

    @PostMapping
    public ResponseEntity<Long> createFood(@RequestBody @Valid FoodRequest foodRequest){
        Long id=foodService.createFood(foodRequest);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@RequestBody @Valid FoodRequest foodRequest,@PathVariable Long id){

        foodService.updateFood(foodRequest,id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable Long id){
        foodService.deleteFood(id);

        return ResponseEntity.ok().build();
    }
}
