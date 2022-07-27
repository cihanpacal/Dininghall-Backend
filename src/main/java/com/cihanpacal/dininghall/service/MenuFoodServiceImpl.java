package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.*;
import com.cihanpacal.dininghall.model.request.MenuFoodRequest;
import com.cihanpacal.dininghall.model.response.MenuFoodResponse;
import com.cihanpacal.dininghall.repository.MenuFoodRepository;
import com.cihanpacal.dininghall.validation.FoodValidator;
import com.cihanpacal.dininghall.validation.MenuFoodValidator;
import com.cihanpacal.dininghall.validation.MenuValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuFoodServiceImpl implements MenuFoodService {

    private final MenuFoodRepository menuFoodRepository;
    private final ModelMapper modelMapper;
    private final MenuFoodValidator menuFoodValidator;
    private final MenuValidator menuValidator;
    private final FoodValidator foodValidator;

    @Override
    public Page<MenuFoodResponse> getMenuFoodsByMenuId(Optional<String> filter,Long menuId, Pageable pageable) {

        menuValidator.checkMenuExist(menuId);

        return menuFoodRepository.findByMenuId(filter.orElse(""),menuId,pageable).map((menuFood)->{
            return modelMapper.map(menuFood, MenuFoodResponse.class);
        });
    }

    @Override
    public Long createMenuFood(MenuFoodRequest menuFoodRequest) {
        Menu menu=menuValidator
                .checkMenuExist(menuFoodRequest.getMenuId());
        Food food=foodValidator
                .checkFoodExist(menuFoodRequest.getFoodId());
        menuFoodValidator
                .checkMenuFoodIsAlreadyExistByMenuIdAndFoodId(
                        menuFoodRequest.getMenuId(),
                        menuFoodRequest.getFoodId()
                );
        menuFoodValidator.checkFoodMustHasAtLeastOneFoodProduct(food.getId());

        MenuFood menuFood=new MenuFood();
        menuFood.setMenu(menu);
        menuFood.setFood(food);
        menuFood.setStatusTime(new Date(0));

        menuFoodRepository.save(menuFood);

        return  menuFood.getId();
    }

    @Override
    public void deleteMenuFood(Long id) {
        MenuFood menuFood=menuFoodValidator.checkMenuFoodExist(id);

        menuFoodRepository.deleteById(menuFood.getId());
    }

    @Override
    public void deleteByMenuId(Long menuId) {
        menuFoodRepository.deleteByMenu_Id(menuId);
    }
}
