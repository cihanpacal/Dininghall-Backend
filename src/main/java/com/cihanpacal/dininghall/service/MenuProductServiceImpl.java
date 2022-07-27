package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.*;
import com.cihanpacal.dininghall.model.request.MenuProductRequest;
import com.cihanpacal.dininghall.model.request.MenuProductUpdateRequest;
import com.cihanpacal.dininghall.model.response.MenuProductResponse;
import com.cihanpacal.dininghall.repository.MenuProductRepository;
import com.cihanpacal.dininghall.validation.*;
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
public class MenuProductServiceImpl implements MenuProductService{

    private final MenuProductRepository menuProductRepository;
    private final ModelMapper modelMapper;
    private final MenuProductValidator menuProductValidator;
    private final MenuValidator menuValidator;
    private final ProductValidator productValidator;

    @Override
    public Page<MenuProductResponse> getMenuProductsByMenuId(Optional<String> filter, Long menuId, Pageable pageable) {
        menuValidator.checkMenuExist(menuId);

        return menuProductRepository.findByMenuId(filter.orElse(""),menuId,pageable).map((menuProduct)->{
            return modelMapper.map(menuProduct, MenuProductResponse.class);
        });
    }

    @Override
    public Long createMenuProduct(MenuProductRequest menuProductRequest) {
        Menu menu=menuValidator
                .checkMenuExist(menuProductRequest.getMenuId());
        Product product=productValidator
                .checkProductExist(menuProductRequest.getProductId());
        menuProductValidator
                .checkMenuProductIsAlreadyExistByMenuIdAndProductId(
                        menuProductRequest.getMenuId(),
                        menuProductRequest.getProductId()
                );

        MenuProduct menuProduct=new MenuProduct();
        menuProduct.setQuantity(menuProductRequest.getQuantity());
        menuProduct.setMenu(menu);
        menuProduct.setProduct(product);
        menuProduct.setStatusTime(new Date(0));

        menuProductRepository.save(menuProduct);

        return  menuProduct.getId();
    }

    @Override
    public void deleteMenuProduct(Long id) {
        MenuProduct menuProduct=menuProductValidator.checkMenuProductExist(id);

        menuProductRepository.deleteById(menuProduct.getId());
    }

    @Override
    public void updateMenuProduct(MenuProductUpdateRequest menuProductUpdateRequest, Long id) {
        MenuProduct menuProduct=menuProductValidator.checkMenuProductExist(id);


        menuProduct.setQuantity(menuProductUpdateRequest.getQuantity());

        menuProductRepository.save(menuProduct);
    }

    @Override
    public void deleteByMenuId(Long menuId) {
        menuProductRepository.deleteByMenu_Id(menuId);
    }
}
