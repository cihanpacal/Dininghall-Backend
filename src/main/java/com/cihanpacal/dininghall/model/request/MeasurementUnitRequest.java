package com.cihanpacal.dininghall.model.request;

import com.cihanpacal.dininghall.validation.NullOrNotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class MeasurementUnitRequest {
    @NotBlank
    @Length(max = 50)
    private String name;

    @NotBlank
    @Length(max = 5)
    private String shortName;

    @NullOrNotBlank
    @Length(max = 255)
    private String description;
}
