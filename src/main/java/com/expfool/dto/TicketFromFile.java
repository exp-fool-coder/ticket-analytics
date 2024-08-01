package com.expfool.dto;

import com.expfool.entity.Ticket;

import java.time.Instant;

public record TicketFromFile(
        String origin,
        String origin_name,
        String destination,
        String destination_name,
        String departure_date,
        String departure_time,
        String arrival_date,
        String arrival_time,
        String carrier,
        int stops,
        int price
) { }
