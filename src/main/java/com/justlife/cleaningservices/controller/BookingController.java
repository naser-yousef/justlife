package com.justlife.cleaningservices.controller;

import com.justlife.cleaningservices.dto.AvailabilityCheck;
import com.justlife.cleaningservices.dto.AvailabilityResponse;
import com.justlife.cleaningservices.dto.BookRequest;
import com.justlife.cleaningservices.dto.BookingResponse;
import com.justlife.cleaningservices.dto.Professional;
import com.justlife.cleaningservices.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/cleaning/v1/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Fetch available times for Professionals")
    @GetMapping("availability")
    public AvailabilityResponse checkAvailability(AvailabilityCheck availabilityCheck) {
        return bookingService.checkAvailability(availabilityCheck);
    }

    @Operation(summary = "Book professionals for period of time")
    @PostMapping
    public BookingResponse book(@Valid @RequestBody BookRequest bookRequest) {
        return bookingService.book(bookRequest);
    }

    @Operation(summary = "Update an appointment")
    @PutMapping("{id}")
    public BookingResponse updateBooking(@Valid @RequestBody BookRequest bookRequest, @PathVariable("id") Long id) {
        return bookingService.update(bookRequest, id);
    }

}
