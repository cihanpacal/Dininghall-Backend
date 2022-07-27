package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.EntityMustHasRelationshipException;
import com.cihanpacal.dininghall.error.ResourceAlreadyExistException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.MenuFood;
import com.cihanpacal.dininghall.repository.MenuFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuFoodValidatorImpl implements MenuFoodValidator {

    private final MenuFoodRepository menuFoodRepository;

    @Override
    public MenuFood checkMenuFoodExist(Long id) {
        Optional<MenuFood> optionalMenuFood=menuFoodRepository.findById(id);
        MenuFood menuFood=optionalMenuFood.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Menü Yemeği"});
        });
        return menuFood;
    }

    @Override
    public void checkMenuFoodIsAlreadyExistByMenuIdAndFoodId(Long menuId, Long foodId) {
        Optional<MenuFood> optionalMenuFood=menuFoodRepository
                .findByMenu_IdAndFood_Id(menuId,foodId);
        optionalMenuFood.ifPresent((menuFood)->{
            throw new ResourceAlreadyExistException(
                    "exception.ResourceAlreadyExistException",
                    new Object[]{"Menü",menuId,"Yemek",foodId,"Menü Yemeği"}
            );
        });
    }

    @Override
    public void checkFoodMustHasAtLeastOneFoodProduct(Long id) {
        long count=menuFoodRepository.getCountOfFoodProductsByFood(id);
        if(count<1){
            throw new EntityMustHasRelationshipException(
                    "exception.EntityMustHasRelationshipException",
                    new Object[]{id,"yemek"}
            );
        }
    }
}
