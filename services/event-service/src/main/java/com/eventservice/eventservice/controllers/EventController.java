package com.eventservice.eventservice.controllers;

import com.eventservice.eventservice.DTO.EventResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @GetMapping("/{id}")
    public EventResponse get(@PathVariable Long id) {
        return new EventResponse(id, "Concert", "Casablanca", "2026-06-01");
    }
}