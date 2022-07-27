package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.FoodGroup;
import com.cihanpacal.dininghall.model.request.FoodGroupRequest;
import com.cihanpacal.dininghall.model.response.FoodGroupResponse;
import com.cihanpacal.dininghall.repository.FoodGroupRepository;
import com.cihanpacal.dininghall.util.OperationMessage;
import com.cihanpacal.dininghall.validation.FoodGroupValidator;
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
public class FoodGroupServiceImpl implements FoodGroupService{

    private final FoodGroupRepository foodGroupRepository;
    private final FoodGroupValidator foodGroupValidator;
    private final ModelMapper modelMapper;

    @Override
    public Page<FoodGroupResponse> getFoodGroups(Optional<String> filter, Pageable pageable) {

        return foodGroupRepository.findAll(filter.orElse(""),pageable).map((foodGroup)->{
            return modelMapper.map(foodGroup, FoodGroupResponse.class);
        });
    }

    @Override
    public Long createFoodGroup(FoodGroupRequest foodGroupRequest) {

        FoodGroup foodGroup=modelMapper.map(foodGroupRequest, FoodGroup.class);
        foodGroupRepository.save(foodGroup);

        return foodGroup.getId();
    }

    @Override
    public FoodGroupResponse getFoodGroup(Long id) {
        FoodGroup foodGroup=foodGroupValidator.checkFoodGroupExist(id);
        FoodGroupResponse foodGroupResponse=modelMapper.map(foodGroup,FoodGroupResponse.class);

        return foodGroupResponse;
    }

    @Override
    public void updateFoodGroup(FoodGroupRequest foodGroupRequest, Long id) {

        FoodGroup foodGroup=foodGroupValidator.checkFoodGroupExist(id);

        foodGroup.setName(foodGroupRequest.getName());
        foodGroup.setDescription(foodGroupRequest.getDescription());

        foodGroupRepository.save(foodGroup);
    }

    @Override
    public void deleteFoodGroup(Long id) {
        FoodGroup foodGroup=foodGroupValidator.checkFoodGroupExist(id);
        foodGroupValidator.checkAtLeastOneFoodExists(foodGroup.getId(), OperationMessage.DELETE);
        foodGroupRepository.deleteById(foodGroup.getId());
    }
}
