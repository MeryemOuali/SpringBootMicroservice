package com.eventservice.eventservice.DTO;
import com.eventservice.eventservice.entities.Venue;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
public class VenueResponse {
    private Long id;
    private String name;
    private String address;
    private String city;
    private int maxCapacity;
}
