package com.cihanpacal.dininghall.model.response;

import com.cihanpacal.dininghall.model.entity.MeasurementUnit;
import com.cihanpacal.dininghall.model.entity.Product;
import com.cihanpacal.dininghall.model.entity.Stock;
import com.cihanpacal.dininghall.model.entity.Warehouse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockResponse extends BaseResponse {

    private Long id;
    private String description;
    private Double quantity;
    private Double unitPrice;
    private Long warehouseId;
    private String warehouseName;
    private Long productId;
    private String productName;
    private Long measurementUnitId;
    private String measurementUnitShortName;

}
