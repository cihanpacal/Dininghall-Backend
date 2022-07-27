package com.cihanpacal.dininghall.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class BillProductRequest {
    @NotNull
    @Positive
    private Double quantity;

    @NotNull
    @Positive
    private Double unitPrice;


    @NotNull
    private Long billId;

    @NotNull
    private Long productId;
}
