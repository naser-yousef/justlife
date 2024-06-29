package com.justlife.cleaningservices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AvailabilityResponse {

    List<Professional> professionals;

}
