package com.cihanpacal.dininghall.controller;


import com.cihanpacal.dininghall.model.request.MealRequest;
import com.cihanpacal.dininghall.model.response.FoodResponse;
import com.cihanpacal.dininghall.model.response.MealProductResponse;
import com.cihanpacal.dininghall.model.response.MealResponse;
import com.cihanpacal.dininghall.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping
    public ResponseEntity<Page<MealResponse>> getMeals(
            @RequestParam Optional<Long> diningHallId,
            @RequestParam Optional<Long> menuId,
            @RequestParam  Optional<String> startDate,
            @RequestParam Optional<String> endDate,
            @RequestParam Optional<String> startTime,
            @RequestParam Optional<String> endTime,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<MealResponse> mealResponsePage=mealService.getMeals(
                diningHallId,
                menuId,
                startDate,
                endDate,
                startTime,
                endTime,
                pageable
        );

        return ResponseEntity.ok(mealResponsePage);
    }

    @GetMapping("/{id}/food-content/products")
    public ResponseEntity<Page<MealProductResponse>> getMealFoodContentProductsByMealId(
            @RequestParam Optional<String> filter,
            @PathVariable(name = "id") Long mealId,
            @RequestParam(name="foodId") Optional<Long> optionalFoodId,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
            ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<MealProductResponse> mealProductResponsePage=mealService
                .getMealFoodContentProductsQuantityByMealId(
                        filter,
                        mealId,
                        optionalFoodId,
                        pageable
                );
        return ResponseEntity.ok(mealProductResponsePage);
    }

    @GetMapping("/{id}/external/products")
    public ResponseEntity<Page<MealProductResponse>> getMealExternalProductsByMealId(
            @RequestParam Optional<String> filter,
            @PathVariable(name="id") Long mealId,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<MealProductResponse> mealProductResponsePage=mealService
                .getMealExternalProductsQuantityByMealId(
                        filter,
                        mealId,
                        pageable
                );
        return ResponseEntity.ok(mealProductResponsePage);
    }

    @GetMapping("/{id}/foods")
    public ResponseEntity<Page<FoodResponse>> getMealFoodByMealId(
            @RequestParam Optional<String> filter,
            @PathVariable Long id,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<FoodResponse> foodResponsePage=mealService.getMealFoodByMealId(filter,id,pageable);

        return ResponseEntity.ok(foodResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealResponse> getMeal(@PathVariable Long id){
        MealResponse mealResponse=mealService.getMeal(id);
        return ResponseEntity.ok(mealResponse);
    }

    @PostMapping
    public ResponseEntity<Long> createMeal(@RequestBody @Valid MealRequest mealRequest){
        Long id=mealService.createMeal(mealRequest);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMeal(@RequestBody @Valid MealRequest mealRequest,@PathVariable Long id){

        mealService.updateMeal(mealRequest,id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeal(@PathVariable Long id){
        mealService.deleteMeal(id);

        return ResponseEntity.ok().build();
    }

}
