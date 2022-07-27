package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.FoodGroupRequest;
import com.cihanpacal.dininghall.model.request.ProductGroupRequest;
import com.cihanpacal.dininghall.model.response.FoodGroupResponse;
import com.cihanpacal.dininghall.model.response.ProductGroupResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FoodGroupService {
    Page<FoodGroupResponse> getFoodGroups(Optional<String> filter, Pageable pageable);


    Long createFoodGroup(FoodGroupRequest foodGroupRequest);

    FoodGroupResponse getFoodGroup(Long id);

    void updateFoodGroup(FoodGroupRequest foodGroupRequest, Long id);

    void deleteFoodGroup(Long id);
}
