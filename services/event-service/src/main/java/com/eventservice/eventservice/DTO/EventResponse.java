package com.eventservice.eventservice.DTO;
import com.eventservice.eventservice.entities.Venue;
import com.eventservice.eventservice.enums.EventStatus;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class EventResponse {
    private Long id;
    private String title;
    private String summary;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private EventStatus status;
    private String organizerName;
    private String categoryName;
    private Venue venue;
    private List<TicketTierResponse> ticketTiers;


}