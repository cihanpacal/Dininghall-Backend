package com.cihanpacal.dininghall.model.request;

import com.cihanpacal.dininghall.validation.NullOrNotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class BillRequest {

    @NotNull
    private LocalDateTime time;

    @NotNull
    private Long warehouseId;

    @NullOrNotBlank
    @Length(max = 255)
    private String description;
}
