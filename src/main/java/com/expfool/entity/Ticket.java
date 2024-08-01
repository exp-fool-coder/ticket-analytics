package com.expfool.entity;

import java.time.Instant;

public record Ticket(
        String origin,
        String origin_name,
        String destination,
        String destination_name,
        Instant departure_datetime,
        Instant arrival_datetime,
        String carrier,
        int stops,
        int price
) {
}
