package com.cihanpacal.dininghall.controller;


import com.cihanpacal.dininghall.model.request.MenuProductRequest;
import com.cihanpacal.dininghall.model.request.MenuProductUpdateRequest;
import com.cihanpacal.dininghall.model.response.MenuProductResponse;
import com.cihanpacal.dininghall.service.MenuProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/menu-products")
@RequiredArgsConstructor
public class MenuProductController {

    private final MenuProductService menuProductService;

    @GetMapping
    public ResponseEntity<Page<MenuProductResponse>> getMenuProductsByMenuId(
            @RequestParam(required = true) Long menuId,
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<MenuProductResponse> menuProductResponsePage=menuProductService.getMenuProductsByMenuId(
                filter,
                menuId,
                pageable
        );

        return ResponseEntity.ok(menuProductResponsePage);
    }

    @PostMapping
    public ResponseEntity<Long> createMenuProduct(
            @RequestBody @Valid MenuProductRequest menuProductRequest
    ){
        Long id=menuProductService.createMenuProduct(menuProductRequest);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMenuProduct(
            @RequestBody @Valid MenuProductUpdateRequest menuProductUpdateRequest,
            @PathVariable Long id
    ){

        menuProductService.updateMenuProduct(menuProductUpdateRequest,id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenuProduct(@PathVariable Long id){
        menuProductService.deleteMenuProduct(id);

        return ResponseEntity.ok().build();
    }

}
