package com.cihanpacal.dininghall.controller;

import com.cihanpacal.dininghall.model.request.FoodProductRequest;
import com.cihanpacal.dininghall.model.request.FoodProductUpdateRequest;
import com.cihanpacal.dininghall.model.response.FoodProductResponse;
import com.cihanpacal.dininghall.service.FoodProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/food-products")
@RequiredArgsConstructor
public class FoodProductController {

    private final FoodProductService foodProductService;

    @GetMapping
    public ResponseEntity<Page<FoodProductResponse>> getFoodProductsByFoodId(
            @RequestParam(required = true) Long foodId,
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<FoodProductResponse> foodProductResponsePage=foodProductService.getFoodProductsByFoodId(
                filter,
                foodId,
                pageable
        );

        return ResponseEntity.ok(foodProductResponsePage);
    }

    @PostMapping
    public ResponseEntity<Long> createFoodProduct(
            @RequestBody @Valid FoodProductRequest foodProductRequest
    ){
        Long id=foodProductService.createFoodProduct(foodProductRequest);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFoodProduct(
            @RequestBody @Valid FoodProductUpdateRequest foodProductUpdateRequest,
            @PathVariable Long id
    ){

        foodProductService.updateFoodProduct(foodProductUpdateRequest,id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFoodProduct(@PathVariable Long id){
        foodProductService.deleteFoodProduct(id);

        return ResponseEntity.ok().build();
    }
}
