package com.justlife.cleaningservices.dto;

import com.justlife.cleaningservices.validators.DurationValidation;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class BookRequest {

    @Size(min = 1, max = 3)
    private Integer numberOfCleaners;

    @DurationValidation
    private Integer duration;

    @Future
    private Date startDate;

}
