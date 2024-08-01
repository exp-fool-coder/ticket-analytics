package com.expfool;

import com.expfool.controller.TicketStatisticController;
import com.expfool.dao.TicketFileReader;
import com.expfool.dto.api.TicketStatisticAnswer;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        TicketFileReader fileReader = new TicketFileReader();
        TicketStatisticController ticketStatisticController = new TicketStatisticController(
                fileReader
        );
        System.out.println("Enter path of file to read:");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        try {
            TicketStatisticAnswer ticketStatisticAnswer = ticketStatisticController.getSomeStatisticFromTicketsFile(path, "VVO", "TLV");
            System.out.println(ticketStatisticAnswer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}