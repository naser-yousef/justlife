package com.justlife.cleaningservices.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AvailabilityCheck {

    private Date date;
    private Date startTime;
    private Integer duration;

}
