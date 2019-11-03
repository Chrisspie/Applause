package com.applause.testermatching;

import com.applause.testermatching.Model.Bug;
import com.applause.testermatching.Services.CsvParserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TesterMatchingApplicationTests {

    @Autowired
    CsvParserService csvParserService;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldSuccessfullyParseFile() {
        File file = Paths.get("src", "test", "resources", "bugs.csv").toFile().getAbsoluteFile();

        List<Bug> bugList = csvParserService.parseFile(file);
        Bug bug = bugList.get(0);
        assertEquals(1000, bugList.size());
        assertEquals(1, bug.getBugId());
        assertEquals(1, bug.getDeviceId());
        assertEquals(4, bug.getTesterId());
    }

    @Test
    void shouldFailWhileParsingFile() {
        File file = Paths.get("src", "test", "resources", "xyz.csv").toFile().getAbsoluteFile();
        assertThrows(RuntimeException.class, () -> csvParserService.parseFile(file));
    }
}
