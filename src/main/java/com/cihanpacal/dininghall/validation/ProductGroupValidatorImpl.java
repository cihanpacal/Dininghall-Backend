package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.EntityHasRelationshipException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.ProductGroup;
import com.cihanpacal.dininghall.repository.ProductGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductGroupValidatorImpl implements ProductGroupValidator {

    private final ProductGroupRepository productGroupRepository;

    @Override
    public ProductGroup checkProductGroupExist(Long id) {
        Optional<ProductGroup> optional=productGroupRepository.findById(id);

        ProductGroup productGroup=optional.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Ürün Grubu"});
        });

        return  productGroup;
    }

    @Override
    public void checkAtLeastOneProductExists(Long id, String operationMessage) {

        long count=productGroupRepository.getCountOfProductsByProductGroup(id);
        String name=productGroupRepository.findById(id).get().getName();
        if(count>0){
            throw new EntityHasRelationshipException(
                    "exception.EntityHasRelationshipException",
                    new Object[]{name,"adlı","ürün grubu",operationMessage,"na",count,"ürün"}
            );
        }
    }
}
