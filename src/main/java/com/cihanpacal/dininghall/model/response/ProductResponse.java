package com.cihanpacal.dininghall.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse extends BaseResponse {

    private Long id;
    private String name;
    private String description;
    private Long productGroupId;
    private String productGroupName;
    private Long measurementUnitId;
    private String measurementUnitName;
    private String measurementUnitShortName;
}
