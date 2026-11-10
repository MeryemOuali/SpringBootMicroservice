package com.eventservice.eventservice.DTO;
import com.eventservice.eventservice.entities.TicketTier;
import lombok.Builder;
import lombok.Data;

public class TicketTierResponse {
    @Data
    @Builder
    public static class TicketTierDto {
        private Long id;
        private String name;
        private java.math.BigDecimal price;
        private int initialQuantity;
        private int availableQuantity;
    }
}
