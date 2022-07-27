package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.ProductRequest;
import com.cihanpacal.dininghall.model.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    Page<ProductResponse> getProducts(
            Optional<String> filter,
            Optional<Long> productGroupId,
            Optional<Long> measurementUnitId,
            Pageable pageable
    );



    Long createProduct(ProductRequest productRequest);

    ProductResponse getProduct(Long id);

    void updateProduct(ProductRequest productRequest, Long id);

    void deleteProduct(Long id);
}
