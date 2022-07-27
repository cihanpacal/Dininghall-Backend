package com.cihanpacal.dininghall.controller;

import com.cihanpacal.dininghall.model.request.FoodGroupRequest;
import com.cihanpacal.dininghall.model.response.FoodGroupResponse;
import com.cihanpacal.dininghall.service.FoodGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/food-groups")
@RequiredArgsConstructor
public class FoodGroupController {

    private final FoodGroupService foodGroupService;

    @GetMapping
    public ResponseEntity<Page<FoodGroupResponse>> getFoodGroups(
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<FoodGroupResponse> foodGroupResponsePage=foodGroupService
                .getFoodGroups(filter,pageable);


        return  ResponseEntity.ok(foodGroupResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodGroupResponse> getFoodGroup(@PathVariable Long id){
        FoodGroupResponse foodGroupResponse=foodGroupService.getFoodGroup(id);
        return ResponseEntity.ok(foodGroupResponse);
    }

    @PostMapping
    public ResponseEntity<Long> createFoodGroup(@RequestBody @Valid FoodGroupRequest foodGroupRequest){
        Long id=foodGroupService.createFoodGroup(foodGroupRequest);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFoodGroup(
            @RequestBody @Valid FoodGroupRequest foodGroupRequest,
            @PathVariable Long id
    ){

        foodGroupService.updateFoodGroup(foodGroupRequest,id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFoodGroup(@PathVariable Long id){

        foodGroupService.deleteFoodGroup(id);

        return ResponseEntity.ok().build();
    }
}
