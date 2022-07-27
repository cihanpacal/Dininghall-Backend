package com.cihanpacal.dininghall.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockTransactionResponse extends BaseResponse{

    private String description;
    private Double quantity;
    private Double unitPrice;
    private LocalDateTime transactionTime;
    private Boolean transactionType;
    private Long productId;
    private String productName;
    private Long warehouseId;
    private String warehouseName;
    private String measurementUnitShortName;
}
