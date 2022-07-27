package com.cihanpacal.dininghall.model.request;

import com.cihanpacal.dininghall.validation.NullOrNotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DiningHallRequest {

    @NotBlank
    @Length(max = 50)
    private String name;

    @NullOrNotBlank
    @Length(max = 255)
    private String description;

    @NotNull
    private Long warehouseId;
}
