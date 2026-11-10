package com.eventservice.eventservice.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ticket_tiers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketTier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Ex: VIP, Standard, Early Bird

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int initialQuantity;

    @Column(nullable = false)
    private int availableQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
