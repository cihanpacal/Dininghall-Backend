package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.FoodProductRequest;
import com.cihanpacal.dininghall.model.request.MenuFoodRequest;
import com.cihanpacal.dininghall.model.response.FoodProductResponse;
import com.cihanpacal.dininghall.model.response.MenuFoodResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MenuFoodService {

    Page<MenuFoodResponse> getMenuFoodsByMenuId(Optional<String> filter,Long menuId, Pageable pageable);
    Long createMenuFood(MenuFoodRequest menuFoodRequest);
    void deleteMenuFood(Long id);
    void deleteByMenuId(Long menuId);
}
