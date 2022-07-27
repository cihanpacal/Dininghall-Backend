package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.EntityMustHasRelationshipException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.Menu;
import com.cihanpacal.dininghall.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuValidatorImpl implements MenuValidator {

    private final MenuRepository menuRepository;

    @Override
    public Menu checkMenuExist(Long id) {
        Optional<Menu> optionalMenu=menuRepository.findById(id);
        Menu menu=optionalMenu.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Menü"});
        });
        return menu;
    }

    @Override
    public void checkMenuMustHasAtLeastOneMenuFood(Long id) {
        long count=menuRepository.getCountOfMenuFoodsByMenu(id);
        if(count<1){
            throw new EntityMustHasRelationshipException(
                    "exception.EntityMustHasRelationshipException",
                    new Object[]{id,"menü"}
            );
        }
    }
}
