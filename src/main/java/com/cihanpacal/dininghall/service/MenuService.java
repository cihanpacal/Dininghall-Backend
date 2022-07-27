package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.request.MenuRequest;
import com.cihanpacal.dininghall.model.response.MenuResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MenuService {
    Page<MenuResponse> getMenus(
            Optional<String> filter,
            Pageable pageable
    );



    Long createMenu(MenuRequest menuRequest);

    MenuResponse getMenu(Long id);

    void updateMenu(MenuRequest menuRequest, Long id);

    void deleteMenu(Long id);
}
