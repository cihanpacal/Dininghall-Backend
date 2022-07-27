package com.cihanpacal.dininghall.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class StockUpdateRequest {

    @NotNull
    @PositiveOrZero
    private Double quantity;

    @NotNull
    @PositiveOrZero
    private Double unitPrice;
}
