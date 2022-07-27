package com.cihanpacal.dininghall.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuProductResponse extends BaseResponse {
    private Double quantity;
    private Long productId;
    private String productName;
    private Long menuId;
    private String productMeasurementUnitShortName;
}
