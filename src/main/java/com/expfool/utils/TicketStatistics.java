package com.expfool.utils;

import com.expfool.entity.Ticket;

import java.time.Duration;
import java.util.*;

import static java.util.stream.Collectors.toCollection;

public class TicketStatistics {

    public static Duration minFlightTime(List<Ticket> tickets) throws Exception {
        Optional<Ticket> ticketWithMinimalFlightTime = tickets.stream().min((ticket1, ticket2) -> {
           long duration1 = ticket1.arrivalDatetime().getEpochSecond() - ticket1.departureDatetime().getEpochSecond();
           long duration2 = ticket2.arrivalDatetime().getEpochSecond() - ticket2.departureDatetime().getEpochSecond();
           return Long.compare(duration1, duration2);
        });

        if (ticketWithMinimalFlightTime.isEmpty()) {
            throw new Exception("There is no tickets at all!!!");
        }

        return Duration.between(
                ticketWithMinimalFlightTime.get().departureDatetime(),
                ticketWithMinimalFlightTime.get().arrivalDatetime()
        );
    }

    public static double getMedianTicketsPrice(List<Ticket> tickets) throws Exception {
        if (tickets.isEmpty()) {
            throw new Exception("There is no tickets at all!!!");
        }
        ArrayList<Ticket> sortedByPriceTickets = tickets.stream()
                .sorted(
                        Comparator.comparingInt(Ticket::price)
                ).collect(toCollection(ArrayList::new));

        int size = sortedByPriceTickets.size();
        if (size % 2 == 0) {
            return (sortedByPriceTickets.get(size / 2).price() + sortedByPriceTickets.get(size / 2 - 1).price()) / 2.0;
        } else {
            return sortedByPriceTickets.get(size / 2 + 1).price();
        }
    }

    public static double getAverageTicketsPrice(List<Ticket> tickets) throws Exception {
        if (tickets.isEmpty()) {
            throw new Exception("There is no tickets at all!!!");
        }
        return tickets.stream().mapToDouble(Ticket::price).average().getAsDouble();
    }
}
