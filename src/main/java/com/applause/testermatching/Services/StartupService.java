package com.applause.testermatching.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StartupService {


    private final CsvParserService csvParserService;

    @Autowired
    public StartupService(CsvParserService csvParserService) {
        this.csvParserService = csvParserService;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void doSomethingAfterStartup(){
        csvParserService.parseAndPersistEntityClasses();
    }


}
