package com.expfool.dto.api;

public record MinFlightTimeOfCarrier(
        String carrier,
        long minFlightTimeInSeconds,
        String formattedMinFlightTime
) {
}
