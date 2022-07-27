package com.cihanpacal.dininghall.model.request;

import com.cihanpacal.dininghall.validation.NullOrNotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class MealRequest {

    @NotNull
    @Positive
    private Integer numberOfPeople;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime time;

    @NullOrNotBlank
    @Length(max = 255)
    private String description;

    @NotNull
    private Long diningHallId;

    @NotNull
    private Long menuId;
}
