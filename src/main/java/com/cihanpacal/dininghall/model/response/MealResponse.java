package com.cihanpacal.dininghall.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MealResponse extends BaseResponse{

    private Integer numberOfPeople;
    private LocalDate date;
    private LocalTime time;
    private String description;
    private Long diningHallId;
    private String diningHallName;
    private Long menuId;
    private String menuName;
}
