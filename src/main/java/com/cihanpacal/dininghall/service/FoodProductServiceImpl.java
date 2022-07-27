package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.*;
import com.cihanpacal.dininghall.model.request.FoodProductRequest;
import com.cihanpacal.dininghall.model.request.FoodProductUpdateRequest;
import com.cihanpacal.dininghall.model.response.FoodProductResponse;
import com.cihanpacal.dininghall.repository.FoodProductRepository;
import com.cihanpacal.dininghall.util.OperationMessage;
import com.cihanpacal.dininghall.validation.FoodProductValidator;
import com.cihanpacal.dininghall.validation.FoodValidator;
import com.cihanpacal.dininghall.validation.ProductValidator;
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
public class FoodProductServiceImpl implements FoodProductService{

    private final FoodProductRepository foodProductRepository;
    private final ModelMapper modelMapper;
    private final FoodProductValidator foodProductValidator;
    private final FoodValidator foodValidator;
    private final ProductValidator productValidator;

    @Override
    public Page<FoodProductResponse> getFoodProductsByFoodId(Optional<String> filter, Long foodId, Pageable pageable) {

        foodValidator.checkFoodExist(foodId);

        return foodProductRepository.findByFoodId(filter.orElse(""),foodId,pageable).map((foodProduct)->{
            return modelMapper.map(foodProduct, FoodProductResponse.class);
        });
    }

    @Override
    public Long createFoodProduct(FoodProductRequest foodProductRequest) {
        Food food=foodValidator
                .checkFoodExist(foodProductRequest.getFoodId());
        Product product=productValidator
                .checkProductExist(foodProductRequest.getProductId());
        foodProductValidator
                .checkFoodProductIsAlreadyExistByFoodIdAndProductId(
                        foodProductRequest.getFoodId(),
                        foodProductRequest.getProductId()
                );

        FoodProduct foodProduct=new FoodProduct();
        foodProduct.setQuantity(foodProductRequest.getQuantity());
        foodProduct.setFood(food);
        foodProduct.setProduct(product);
        foodProduct.setStatusTime(new Date(0));

        foodProductRepository.save(foodProduct);

        return  foodProduct.getId();
    }

    @Override
    public void deleteFoodProduct(Long id) {
        FoodProduct foodProduct=foodProductValidator.checkFoodProductExist(id);

        foodProductValidator.checkAtLeastOneMealExists(id, OperationMessage.DELETE);

        foodProductRepository.deleteById(foodProduct.getId());
    }

    @Override
    public void updateFoodProduct(FoodProductUpdateRequest foodProductUpdateRequest, Long id) {
        FoodProduct foodProduct=foodProductValidator.checkFoodProductExist(id);

        foodProductValidator.checkAtLeastOneMealExists(id, OperationMessage.UPDATE);

        foodProduct.setQuantity(foodProductUpdateRequest.getQuantity());

        foodProductRepository.save(foodProduct);
    }

    @Override
    public void deleteByFoodId(Long foodId) {
        foodProductRepository.deleteByFood_Id(foodId);
    }
}
