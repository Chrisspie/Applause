package com.applause.testermatching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class TesterMatchingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TesterMatchingApplication.class, args);
    }


}
