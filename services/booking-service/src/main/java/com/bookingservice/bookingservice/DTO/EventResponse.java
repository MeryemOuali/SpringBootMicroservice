package com.bookingservice.bookingservice.DTO;

public record EventResponse(
        Long id,
        String name,
        String location,
        String date
) {}
