package com.cihanpacal.dininghall.controller;

import com.cihanpacal.dininghall.model.request.MenuRequest;
import com.cihanpacal.dininghall.model.response.MenuResponse;
import com.cihanpacal.dininghall.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<Page<MenuResponse>> getMenuss(
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<MenuResponse> menuResponsePage=menuService.getMenus(
                filter,
                pageable
        );

        return ResponseEntity.ok(menuResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> getMenu(@PathVariable Long id){
        MenuResponse menuResponse=menuService.getMenu(id);
        return ResponseEntity.ok(menuResponse);
    }

    @PostMapping
    public ResponseEntity<Long> createMenu(@RequestBody @Valid MenuRequest menuRequest){
        Long id=menuService.createMenu(menuRequest);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMen(@RequestBody @Valid MenuRequest menuRequest,@PathVariable Long id){

        menuService.updateMenu(menuRequest,id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable Long id){
        menuService.deleteMenu(id);

        return ResponseEntity.ok().build();
    }

}
