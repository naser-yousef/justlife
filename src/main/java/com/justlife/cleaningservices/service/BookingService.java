package com.justlife.cleaningservices.service;

import com.justlife.cleaningservices.dto.AvailabilityCheck;
import com.justlife.cleaningservices.dto.AvailabilityResponse;
import com.justlife.cleaningservices.dto.BookRequest;
import com.justlife.cleaningservices.dto.BookingResponse;

public interface BookingService {

    AvailabilityResponse checkAvailability(AvailabilityCheck availabilityCheck);

    BookingResponse book(BookRequest bookRequest);

    BookingResponse update(BookRequest bookRequest, Long id);

}
