package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.ResourceAlreadyExistException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.MenuProduct;
import com.cihanpacal.dininghall.repository.MenuProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class MenuProductValidatorImpl implements MenuProductValidator{

    private final MenuProductRepository menuProductRepository;

    @Override
    public MenuProduct checkMenuProductExist(Long id) {
        Optional<MenuProduct> optionalMenuProduct=menuProductRepository.findById(id);
        MenuProduct menuProduct=optionalMenuProduct.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Menü Ürünü"});
        });
        return menuProduct;
    }

    @Override
    public void checkMenuProductIsAlreadyExistByMenuIdAndProductId(Long menuId, Long productId) {
        Optional<MenuProduct> optionalMenuProduct=menuProductRepository
                .findByMenu_IdAndProduct_Id(menuId,productId);
        optionalMenuProduct.ifPresent((menuProduct)->{
            throw new ResourceAlreadyExistException(
                    "exception.ResourceAlreadyExistException",
                    new Object[]{"Menü Ürünü"}
            );
        });
    }
}
