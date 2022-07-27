package com.cihanpacal.dininghall.controller;

import com.cihanpacal.dininghall.model.request.ProductGroupRequest;
import com.cihanpacal.dininghall.model.response.ProductGroupResponse;
import com.cihanpacal.dininghall.service.ProductGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/product-groups")
@RequiredArgsConstructor
public class ProductGroupController {

    private final ProductGroupService productGroupService;

    @GetMapping
    public ResponseEntity<Page<ProductGroupResponse>> getProductGroups(
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){

        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<ProductGroupResponse> productGroupResponsePage=productGroupService
               .getProductGroups(filter,pageable);


        return  ResponseEntity.ok(productGroupResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGroupResponse> getProductGroup(@PathVariable Long id){
        ProductGroupResponse productGroupResponse=productGroupService.getProductGroup(id);
        return ResponseEntity.ok(productGroupResponse);
    }

    @PostMapping
    public ResponseEntity<Long> createProductGroup(@RequestBody @Valid ProductGroupRequest productGroupRequest){
        Long id=productGroupService.createProductGroup(productGroupRequest);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductGroup(
            @RequestBody @Valid ProductGroupRequest productGroupRequest,
            @PathVariable Long id
    ){

        productGroupService.updateProductGroup(productGroupRequest,id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductGroup(@PathVariable Long id){

        productGroupService.deleteProductGroup(id);

        return ResponseEntity.ok().build();
    }

}
