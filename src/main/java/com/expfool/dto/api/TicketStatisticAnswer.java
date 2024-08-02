package com.expfool.dto.api;

import java.util.List;

public record TicketStatisticAnswer(
        double averagePrice,
        double medianPrice,
        double differenceBetweenAverageAndMedianPrice,
        List<MinFlightTimeOfCarrier> minFlightTimeOfCarriers
) {
}
