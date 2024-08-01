package com.expfool.dao;

import com.expfool.dto.jsonfiles.TicketFromFile;
import com.expfool.dto.jsonfiles.TicketsFromFile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TicketFileReader {

    public List<TicketFromFile> readTicketsFile(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TicketsFromFile tickets = mapper.readValue(new File(fileName), new TypeReference<TicketsFromFile>() {});
        return tickets.tickets();
    }

}
