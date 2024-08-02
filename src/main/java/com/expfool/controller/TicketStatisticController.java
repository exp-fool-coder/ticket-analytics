package com.expfool.controller;

import com.expfool.dao.TicketFileReader;
import com.expfool.dto.api.MinFlightTimeOfCarrier;
import com.expfool.dto.api.TicketStatisticAnswer;
import com.expfool.dto.jsonfiles.TicketFromFile;
import com.expfool.entity.Ticket;
import com.expfool.utils.TicketStatistics;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TicketStatisticController {

    private final TicketFileReader ticketFileReader;

    public TicketStatisticController(
            TicketFileReader ticketFileReader
    ) {
        this.ticketFileReader = ticketFileReader;
    }

    public TicketStatisticAnswer getSomeStatisticFromTicketsFile(
            String ticketsFilePath,
            String origin,
            String destination
    ) throws Exception {
        List<TicketFromFile> ticketsFromFile = ticketFileReader.readTicketsFile(ticketsFilePath);
        List<Ticket> tickets = ticketsFromFile.stream()
                .filter(ticket -> Objects.equals(ticket.origin(), origin) && Objects.equals(ticket.destination(), destination))
                .map(TicketFromFile::toTicket).toList();

        double averageTicketPrice = TicketStatistics.getAverageTicketsPrice(tickets);
        double medianTicketPrice = TicketStatistics.getMedianTicketsPrice(tickets);
        Map<String, Duration> minFlightTimeByEveryCarrier = TicketStatistics.minFlightTimeByEveryCarrier(tickets);

        List<MinFlightTimeOfCarrier> minFlightTimeByEveryCarrierAnswer = minFlightTimeByEveryCarrier.entrySet().stream()
                .map(entry -> new MinFlightTimeOfCarrier(
                        entry.getKey(),
                        entry.getValue().toSeconds(),
                        entry.getValue().toHours()  + ":" + entry.getValue().toMinutesPart()
                )).toList();


        return new TicketStatisticAnswer(
                averageTicketPrice,
                medianTicketPrice,
                Math.abs(averageTicketPrice - medianTicketPrice),
                minFlightTimeByEveryCarrierAnswer
        );
    }

}
