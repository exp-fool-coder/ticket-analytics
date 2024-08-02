package com.expfool.utils;

import com.expfool.entity.Ticket;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class TicketStatistics {

    public static Map<String, Duration> minFlightTimeByEveryCarrier(List<Ticket> tickets) throws Exception {
        if (tickets == null || tickets.isEmpty()) {
            throw new Exception("There is no tickets at all!!!");
        }
        Map<String, List<Ticket>> ticketsMapdByCarrier = tickets.stream()
                .collect(Collectors.groupingBy(Ticket::carrier));

        return ticketsMapdByCarrier.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            List<Ticket> ts = entry.getValue();
                            Ticket shortestTicket = ts.stream().min((ticket1, ticket2) -> {
                                long duration1 = ticket1.arrivalDatetime().getEpochSecond() - ticket1.departureDatetime().getEpochSecond();
                                long duration2 = ticket2.arrivalDatetime().getEpochSecond() - ticket2.departureDatetime().getEpochSecond();
                                return Long.compare(duration1, duration2);
                            }).get();
                            return Duration.between(
                                    shortestTicket.departureDatetime(),
                                    shortestTicket.arrivalDatetime()
                            );
                        }
                ));
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
