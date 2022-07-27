package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.FoodRequest;
import com.cihanpacal.dininghall.model.request.ProductRequest;
import com.cihanpacal.dininghall.model.response.FoodResponse;
import com.cihanpacal.dininghall.model.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FoodService {

    Page<FoodResponse> getFoods(
            Optional<String> filter,
            Optional<Long> optionalFoodGroupId,
            Pageable pageable
    );



    Long createFood(FoodRequest foodRequest);

    FoodResponse getFood(Long id);

    void updateFood(FoodRequest foodRequest, Long id);

    void deleteFood(Long id);

}
