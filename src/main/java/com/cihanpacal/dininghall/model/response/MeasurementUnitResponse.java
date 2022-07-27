package com.cihanpacal.dininghall.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeasurementUnitResponse extends BaseResponse{
    private String name;
    private String shortName;
    private String description;
}
