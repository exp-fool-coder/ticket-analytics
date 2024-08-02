package com.expfool.dto.jsonfiles;

import com.expfool.entity.Cities;
import com.expfool.entity.Ticket;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
) {

    public Ticket toTicket() {
        var dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");
        Instant departure = LocalDateTime.parse(
                departure_date + " " + departure_time,
                dateFormatter
        ).atZone(ZoneId.of("UTC")).toInstant();
        //atZone(Cities.valueOf(origin).timeZone.toZoneId()).toInstant();

        Instant arrival = LocalDateTime.parse(
                arrival_date + " " + arrival_time,
                dateFormatter
        ).atZone(ZoneId.of("UTC")).toInstant();
        //atZone(Cities.valueOf(destination).timeZone.toZoneId()).toInstant();

        return new Ticket(
                origin,
                origin_name,
                destination,
                destination_name,
                departure,
                arrival,
                carrier,
                stops,
                price
        );
    }
}
