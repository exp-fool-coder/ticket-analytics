package com.expfool.entity;

import java.time.Instant;

public record Ticket(
        String origin,
        String originName,
        String destination,
        String destinationName,
        Instant departureDatetime,
        Instant arrivalDatetime,
        String carrier,
        int stops,
        int price
) { }
