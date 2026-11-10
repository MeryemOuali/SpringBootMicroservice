package com.eventservice.eventservice.services;

import com.eventservice.eventservice.DTO.EventResponse;
import com.eventservice.eventservice.entities.Event;
import com.eventservice.eventservice.enums.EventStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventService {

    private final EventRepository eventRepository;

    /**
     * Recherche publique paginée avec filtres multicritères (FR-001, FR-002)
     * Force le statut à PUBLISHED pour interdire l'accès aux brouillons.
     */
    public Page<EventResponse> searchPublicEvents(
            String keyword,
            Long categoryId,
            String city,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    ) {
        Page<Event> events = eventRepository.searchPublicEvents(
                EventStatus.PUBLISHED,
                keyword,
                categoryId,
                city,
                startDate,
                endDate,
                pageable
        );

        return events.map(this::mapToResponseDto);
    }

    /**
     * Consultation d'une fiche événement spécifique (FR-003)
     * Retourne une exception 404 si l'événement n'est pas trouvé ou s'il est encore en brouillon.
     */
    public EventResponseDto getPublicEventById(Long id) {
        Event event = eventRepository.findByIdAndStatus(id, EventStatus.PUBLISHED)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé ou non disponible publiquement"));

        return mapToResponseDto(event);
    }

    /**
     * Mapper l'entité Event vers EventResponseDto
     */
    private EventResponseDto mapToResponseDto(Event event) {
        return EventResponseDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .summary(event.getSummary())
                .description(event.getDescription())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .status(event.getStatus())
                .organizerName(event.getOrganizerName())
                .categoryName(event.getCategory() != null ? event.getCategory().getName() : null)
                .venue(event.getVenue() != null ? EventResponseDto.VenueDto.builder()
                        .id(event.getVenue().getId())
                        .name(event.getVenue().getName())
                        .address(event.getVenue().getAddress())
                        .city(event.getVenue().getCity())
                        .maxCapacity(event.getVenue().getMaxCapacity())
                        .build() : null)
                .ticketTiers(event.getTicketTiers().stream().map(tier ->
                        EventResponseDto.TicketTierDto.builder()
                                .id(tier.getId())
                                .name(tier.getName())
                                .price(tier.getPrice())
                                .initialQuantity(tier.getInitialQuantity())
                                .availableQuantity(tier.getAvailableQuantity())
                                .build()
                ).toList())
                .build();
    }
}