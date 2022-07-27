package com.cihanpacal.dininghall.service;

import com.cihanpacal.dininghall.model.entity.DiningHall;
import com.cihanpacal.dininghall.model.entity.Meal;
import com.cihanpacal.dininghall.model.entity.Menu;
import com.cihanpacal.dininghall.model.request.MealRequest;
import com.cihanpacal.dininghall.model.response.FoodResponse;
import com.cihanpacal.dininghall.model.response.MealProductResponse;
import com.cihanpacal.dininghall.model.response.MealResponse;
import com.cihanpacal.dininghall.repository.MealRepository;
import com.cihanpacal.dininghall.validation.DiningHallValidator;
import com.cihanpacal.dininghall.validation.MealValidator;
import com.cihanpacal.dininghall.validation.MenuValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final ModelMapper modelMapper;
    private final MealValidator mealValidator;
    private final DiningHallValidator diningHallValidator;
    private final MenuValidator menuValidator;

    @Override
    public Page<MealResponse> getMeals(
            Optional<Long> optionalDiningHallId,
            Optional<Long> optionalMenuId,
            Optional<String> optionalStartDate,
            Optional<String> optionalEndDate,
            Optional<String> optionalStartTime,
            Optional<String> optionalEndTime,
            Pageable pageable
    ) {


        Long diningHallId=optionalDiningHallId.orElse(null);
        Long menuId=optionalMenuId.orElse(null);
        LocalDate startDate=LocalDate.parse(optionalStartDate.orElse("1970-01-01"));
        LocalDate endDate=LocalDate.parse(optionalEndDate.orElse("2100-01-01"));
        LocalTime startTime=LocalTime.parse(optionalStartTime.orElse(LocalTime.MIN.toString()));
        LocalTime endTime=LocalTime.parse(optionalEndTime.orElse(LocalTime.MAX.toString()));


        return mealRepository.findAll(diningHallId,menuId,startDate,endDate,startTime,endTime,pageable).map((meal)->{
                return modelMapper.map(meal, MealResponse.class);
        });
    }

    @Override
    public Long createMeal(MealRequest mealRequest) {

        DiningHall diningHall=diningHallValidator.checkDiningHallExist(mealRequest.getDiningHallId());
        Menu menu=menuValidator.checkMenuExist(mealRequest.getMenuId());
        menuValidator.checkMenuMustHasAtLeastOneMenuFood(mealRequest.getMenuId());

        Meal meal=new Meal();
        meal.setNumberOfPeople(mealRequest.getNumberOfPeople());
        meal.setDate(mealRequest.getDate());
        meal.setTime(mealRequest.getTime());
        meal.setDiningHall(diningHall);
        meal.setMenu(menu);
        meal.setDescription(mealRequest.getDescription());
        meal.setStatusTime(new Date(0));

        mealRepository.save(meal);

        return meal.getId();
    }

    @Override
    public MealResponse getMeal(Long id) {
        Meal meal=mealValidator.checkMealExist(id);
        MealResponse mealResponse=modelMapper.map(meal,MealResponse.class);
        return mealResponse;
    }

    @Override
    public void updateMeal(MealRequest mealRequest, Long id) {
        Meal meal=mealValidator.checkMealExist(id);
        if(meal.getMenu().getId()!=mealRequest.getMenuId()){
            mealValidator.checkAtLeastOneMealStockOutExists(id);
        }

        Menu menu=menuValidator.checkMenuExist(mealRequest.getMenuId());
        menuValidator.checkMenuMustHasAtLeastOneMenuFood(mealRequest.getMenuId());

        DiningHall diningHall=diningHallValidator.checkDiningHallExist(mealRequest.getDiningHallId());
        meal.setNumberOfPeople(mealRequest.getNumberOfPeople());
        meal.setDate(mealRequest.getDate());
        meal.setTime(mealRequest.getTime());
        meal.setDescription(mealRequest.getDescription());
        meal.setDiningHall(diningHall);
        meal.setMenu(menu);

        mealRepository.save(meal);
    }

    @Override
    public void deleteMeal(Long id) {

        Meal meal=mealValidator.checkMealExist(id);
        mealValidator.checkAtLeastOneMealStockOutExists(id);

        mealRepository.deleteById(meal.getId());

    }

    @Override
    public Page<MealProductResponse> getMealFoodContentProductsQuantityByMealId(
            Optional<String> filter,
            Long mealId,Optional<Long> optionalFoodId,
            Pageable pageable) {

        mealValidator.checkMealExist(mealId);
        Long foodId=optionalFoodId.orElse(null);


        return mealRepository
                .findMealFoodContentProductsQuantityByMealId(
                        filter.orElse(""),
                        mealId,
                        foodId,
                        pageable
                );
    }

    @Override
    public Page<MealProductResponse> getMealExternalProductsQuantityByMealId(
            Optional<String> filter,
            Long mealId,
            Pageable pageable
    ) {

        mealValidator.checkMealExist(mealId);

        return mealRepository
                .findMealExternalProductsQuantityByMealId(
                        filter.orElse(""),
                        mealId,
                        pageable
                );
    }

    @Override
    public Page<FoodResponse> getMealFoodByMealId(Optional<String> filter, Long mealId, Pageable pageable) {

        mealValidator.checkMealExist(mealId);

        return mealRepository.findMealFoodByMealId(
                filter.orElse(""),
                mealId,
                pageable
        ).map((food)->{
            return modelMapper.map(food,FoodResponse.class);
        });
    }


}
