package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.FoodProductRequest;
import com.cihanpacal.dininghall.model.request.FoodProductUpdateRequest;
import com.cihanpacal.dininghall.model.request.MenuProductRequest;
import com.cihanpacal.dininghall.model.request.MenuProductUpdateRequest;
import com.cihanpacal.dininghall.model.response.FoodProductResponse;
import com.cihanpacal.dininghall.model.response.MenuProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MenuProductService {
    Page<MenuProductResponse> getMenuProductsByMenuId(Optional<String> filter, Long menuId, Pageable pageable);
    Long createMenuProduct(MenuProductRequest menuProductRequest);
    void deleteMenuProduct(Long id);
    void updateMenuProduct(MenuProductUpdateRequest menuProductUpdateRequest, Long id);
    void deleteByMenuId(Long menuId);
}
