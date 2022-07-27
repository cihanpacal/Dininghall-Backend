package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.FoodProductRequest;
import com.cihanpacal.dininghall.model.request.FoodProductUpdateRequest;
import com.cihanpacal.dininghall.model.response.FoodProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FoodProductService {

    Page<FoodProductResponse> getFoodProductsByFoodId(Optional<String> filter, Long foodId, Pageable pageable);
    Long createFoodProduct(FoodProductRequest foodProductRequest);
    void deleteFoodProduct(Long id);
    void updateFoodProduct(FoodProductUpdateRequest foodProductUpdateRequest, Long id);
    void deleteByFoodId(Long foodId);

}
