package com.cihanpacal.dininghall.controller;


import com.cihanpacal.dininghall.model.request.MenuFoodRequest;
import com.cihanpacal.dininghall.model.response.MenuFoodResponse;
import com.cihanpacal.dininghall.service.MenuFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/menu-foods")
@RequiredArgsConstructor
public class MenuFoodController {

    private final MenuFoodService menuFoodService;

    @GetMapping
    public ResponseEntity<Page<MenuFoodResponse>> getMenuFoodsByMenuId(
            @RequestParam(required = true) Long menuId,
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<MenuFoodResponse> menuFoodResponsePage=menuFoodService.getMenuFoodsByMenuId(
                filter,
                menuId,
                pageable
        );

        return ResponseEntity.ok(menuFoodResponsePage);
    }

    @PostMapping
    public ResponseEntity<Long> createMenuFood(
            @RequestBody @Valid MenuFoodRequest menuFoodRequest
    ){
        Long id=menuFoodService.createMenuFood(menuFoodRequest);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuFood(@PathVariable Long id){
        menuFoodService.deleteMenuFood(id);

        return ResponseEntity.ok().build();
    }
}
