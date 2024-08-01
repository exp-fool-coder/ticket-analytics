package com.expfool.services;

import com.expfool.entity.Ticket;

import java.time.Duration;
import java.util.*;

import static java.util.stream.Collectors.toCollection;

public class TicketStatistics {

    public Duration minFlightTime(List<Ticket> tickets) throws Exception {
        Optional<Ticket> ticketWithMinimalFlightTime = tickets.stream().min((ticket1, ticket2) -> {
           long duration1 = ticket1.arrival_datetime().getEpochSecond() - ticket1.departure_datetime().getEpochSecond();
           long duration2 = ticket2.arrival_datetime().getEpochSecond() - ticket2.departure_datetime().getEpochSecond();
           return Long.compare(duration1, duration2);
        });

        if (ticketWithMinimalFlightTime.isEmpty()) {
            throw new Exception("There is no tickets at all!!!");
        }

        return Duration.between(
                ticketWithMinimalFlightTime.get().departure_datetime(),
                ticketWithMinimalFlightTime.get().arrival_datetime()
        );
    }

    public int getMedianTicketsPrice(List<Ticket> tickets) throws Exception {
        if (tickets.isEmpty()) {
            throw new Exception("There is no tickets at all!!!");
        }
        ArrayList<Ticket> sortedByPriceTickets = tickets.stream()
                .sorted(
                        Comparator.comparingInt(Ticket::price)
                ).collect(toCollection(ArrayList::new));

        int size = sortedByPriceTickets.size();
        if (size % 2 == 0) {
            return (sortedByPriceTickets.get(size / 2).price() + sortedByPriceTickets.get(size / 2 - 1).price()) / 2;
        } else {
            return sortedByPriceTickets.get(size / 2 + 1).price();
        }
    }

    public double getAverageTicketsPrice(List<Ticket> tickets) throws Exception {
        if (tickets.isEmpty()) {
            throw new Exception("There is no tickets at all!!!");
        }
        return tickets.stream().mapToDouble(Ticket::price).average().getAsDouble();
    }
}
