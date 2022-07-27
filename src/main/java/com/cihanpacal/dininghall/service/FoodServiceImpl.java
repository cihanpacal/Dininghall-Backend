package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.*;
import com.cihanpacal.dininghall.model.request.FoodRequest;
import com.cihanpacal.dininghall.model.response.FoodResponse;
import com.cihanpacal.dininghall.repository.FoodRepository;
import com.cihanpacal.dininghall.util.OperationMessage;
import com.cihanpacal.dininghall.validation.FoodGroupValidator;
import com.cihanpacal.dininghall.validation.FoodValidator;
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
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final ModelMapper modelMapper;
    private final FoodValidator foodValidator;
    private final FoodGroupValidator foodGroupValidator;
    private final FoodProductService foodProductService;

    @Override
    public Page<FoodResponse> getFoods(Optional<String> filter, Optional<Long> optionalFoodGroupId ,Pageable pageable) {

        Long foodGroupId=optionalFoodGroupId.orElse(null);

        return foodRepository.findAll(filter.orElse(""),foodGroupId,pageable).map((food)->{
            return modelMapper.map(food,FoodResponse.class);
        });
    }

    @Override
    public Long createFood(FoodRequest foodRequest) {

        FoodGroup foodGroup=foodGroupValidator
                .checkFoodGroupExist(foodRequest.getFoodGroupId());

        Food food=new Food();
        food.setName(foodRequest.getName());
        food.setDescription(foodRequest.getDescription());
        food.setFoodGroup(foodGroup);

        foodRepository.save(food);

        return food.getId();
    }

    @Override
    public FoodResponse getFood(Long id) {
        Food food=foodValidator.checkFoodExist(id);
        FoodResponse foodResponse=modelMapper.map(food,FoodResponse.class);
        return foodResponse;
    }

    @Override
    public void updateFood(FoodRequest foodRequest, Long id) {
        Food food=foodValidator.checkFoodExist(id);
        FoodGroup foodGroup=foodGroupValidator
                .checkFoodGroupExist(foodRequest.getFoodGroupId());

        food.setName(foodRequest.getName());
        food.setDescription(foodRequest.getDescription());
        food.setFoodGroup(foodGroup);

        foodRepository.save(food);
    }

    @Override
    public void deleteFood(Long id) {
        Food food=foodValidator.checkFoodExist(id);
        foodValidator.checkAtLeastOneMenuFoodExists(id, OperationMessage.DELETE);

        foodProductService.deleteByFoodId(id);

        foodRepository.deleteById(food.getId());
    }


}
