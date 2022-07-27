package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.Menu;
import com.cihanpacal.dininghall.model.request.MenuRequest;
import com.cihanpacal.dininghall.model.response.MenuResponse;
import com.cihanpacal.dininghall.repository.MenuRepository;
import com.cihanpacal.dininghall.validation.MenuValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;
    private final MenuValidator menuValidator;
    private final MenuFoodService menuFoodService;
    private final MenuProductService menuProductService;

    @Override
    public Page<MenuResponse> getMenus(Optional<String> filter, Pageable pageable) {
        return menuRepository.findAll(filter.orElse(""),pageable).map((menu)->{
            return modelMapper.map(menu, MenuResponse.class);
        });
    }

    @Override
    public Long createMenu(MenuRequest menuRequest) {
        Menu menu=new Menu();
        menu.setName(menuRequest.getName());
        menu.setDescription(menuRequest.getDescription());;

        menuRepository.save(menu);

        return menu.getId();
    }

    @Override
    public MenuResponse getMenu(Long id) {
        Menu menu=menuValidator.checkMenuExist(id);
        MenuResponse menuResponse=modelMapper.map(menu,MenuResponse.class);
        return menuResponse;
    }

    @Override
    public void updateMenu(MenuRequest menuRequest, Long id) {
        Menu menu=menuValidator.checkMenuExist(id);

        menu.setName(menuRequest.getName());
        menu.setDescription(menuRequest.getDescription());

        menuRepository.save(menu);
    }

    @Override
    public void deleteMenu(Long id) {
        Menu menu=menuValidator.checkMenuExist(id);


        menuFoodService.deleteByMenuId(id);
        menuProductService.deleteByMenuId(id);

        menuRepository.deleteById(menu.getId());
    }


}
