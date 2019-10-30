package com.applause.testermatching;

import com.applause.testermatching.Services.CsvParserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class TesterMatchingApplicationTests {

    @Autowired
    CsvParserService csvParserService;

    @Test
    void contextLoads() {
    }


    @Test
    void parseFile(){
        File file = new File("task description/bugs.csv");
        csvParserService.parseFile(file);
    }
}
