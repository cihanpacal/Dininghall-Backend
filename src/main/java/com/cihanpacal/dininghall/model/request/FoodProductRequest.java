package com.cihanpacal.dininghall.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class FoodProductRequest {

    @NotNull
    @Positive
    private Double quantity;


    @NotNull
    private Long foodId;

    @NotNull
    private Long productId;
}
