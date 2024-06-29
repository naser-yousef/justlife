package com.justlife.cleaningservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class BookingResponse {

    private List<Long> professionalIds;
    private Long appointmentId;

}
