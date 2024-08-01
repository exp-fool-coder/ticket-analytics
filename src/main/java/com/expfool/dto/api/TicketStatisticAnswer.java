package com.expfool.dto.api;

public record TicketStatisticAnswer(
        double averagePrice,
        double medianPrice,
        double differenceBetweenAverageAndMedianPrice,
        long minFlightTimeInSeconds,
        String formattedMinFlightTime
) {
}
