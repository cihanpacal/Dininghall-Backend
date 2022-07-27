package com.cihanpacal.dininghall.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MenuFoodRequest {

    @NotNull
    private Long menuId;

    @NotNull
    private Long foodId;

}
