package com.cihanpacal.dininghall.controller;

import com.cihanpacal.dininghall.model.request.ProductRequest;
import com.cihanpacal.dininghall.model.response.ProductResponse;
import com.cihanpacal.dininghall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getProducts(
            @RequestParam Optional<String> filter,
            @RequestParam Optional<Long> productGroupId,
            @RequestParam Optional<Long> measurementUnitId,
            @RequestParam Optional<Boolean> unpaged,
            Pageable pageable
    ){
        if(unpaged.isPresent() && unpaged.get()){
            pageable=Pageable.unpaged();
        }

        Page<ProductResponse> productResponsePage=productService.getProducts(
                    filter,
                    productGroupId,
                    measurementUnitId,
                    pageable
            );

        return ResponseEntity.ok(productResponsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id){
        ProductResponse productResponse=productService.getProduct(id);
        return ResponseEntity.ok(productResponse);
    }

    @PostMapping
    public ResponseEntity<Long> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Long id=productService.createProduct(productRequest);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductRequest productRequest,@PathVariable Long id){

        productService.updateProduct(productRequest,id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);

        return ResponseEntity.ok().build();
    }




}
