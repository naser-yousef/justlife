package com.justlife.cleaningservices.dto;

import com.justlife.cleaningservices.entity.HasId;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Professional implements HasId<Long> {

    private Long id;
    private String name;
    private List<Integer> availableHours;
    private Integer vehicleId;

}
