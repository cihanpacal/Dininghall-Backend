package com.cihanpacal.dininghall.model.request;

import com.cihanpacal.dininghall.validation.NullOrNotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class StockTransactionRequest {

    @NotNull
    @Positive
    private Double quantity;


    @NullOrNotBlank
    @Length(max = 255)
    private String description;

    @NotNull
    private Long productId;

    @NotNull
    private Long warehouseId;

}
