package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.error.EntityHasRelationshipException;
import com.cihanpacal.dininghall.error.ResourceNotFoundException;
import com.cihanpacal.dininghall.model.entity.Product;
import com.cihanpacal.dininghall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductValidatorImpl implements ProductValidator{

    private final ProductRepository productRepository;

    @Override
    public Product checkProductExist(Long id) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        Product product=optionalProduct.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Ürün"});
        });
        return product;
    }

    @Override
    public void checkAtLeastOneStockExists(Long id,String operationMessage) {
        long count=productRepository.getCountOfStocksByProductId(id);
        String name=productRepository.findById(id).get().getName();
        if(count>0){
            throw new EntityHasRelationshipException(
                    "exception.EntityHasRelationshipException",
                    new Object[]{name,"adlı","ürün",operationMessage,"ne",count,"stok"}
            );
        }
    }

    @Override
    public void checkAtLeastOneFoodProductExists(Long id,String operationMessage) {
        long count=productRepository.getCountOfFoodProductsByProductId(id);
        String name=productRepository.findById(id).get().getName();
        if(count>0){
            throw new EntityHasRelationshipException(
                    "exception.EntityHasRelationshipException",
                    new Object[]{name,"adlı","ürün",operationMessage,"ne",count,"yemek içeriği"}
            );
        }
    }

    @Override
    public void checkAtLeastOneBillDetailExists(Long id) {
        boolean flag=false;
        if(flag){
            throw new RuntimeException("deneme");
        }
    }

    @Override
    public void checkAtLeastOneMenuProductExists(Long id,String operationMessage) {
        long count=productRepository.getCountOfMenuProductsByProductId(id);
        String name=productRepository.findById(id).get().getName();
        if(count>0){
            throw new EntityHasRelationshipException(
                    "exception.EntityHasRelationshipException",
                    new Object[]{name,"adlı","ürün",operationMessage,"ne",count,"menü içeriği"}
            );
        }
    }
}
