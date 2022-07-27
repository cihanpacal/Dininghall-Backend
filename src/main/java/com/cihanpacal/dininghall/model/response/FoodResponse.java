package com.cihanpacal.dininghall.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodResponse extends BaseResponse {
    private String name;
    private String description;
    private Long foodGroupId;
    private String foodGroupName;
}
