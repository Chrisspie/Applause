package com.applause.testermatching;

import com.applause.testermatching.Model.Dto.TesterWithExperience;
import com.applause.testermatching.Model.SearchCriteria;
import com.applause.testermatching.Services.CsvParserService;
import com.applause.testermatching.Services.SearchService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchServiceTest {

    @Autowired
    CsvParserService csvParserService;

    @Autowired
    SearchService searchService;

    private File bugsFile;
    private File devicesFile;
    private File testerDeviceFile;
    private File testersFile;


    @BeforeAll
    void loadFiles() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        bugsFile = resourceDirectory.resolve("bugs.csv").toFile().getAbsoluteFile();
        testerDeviceFile = resourceDirectory.resolve("tester_device.csv").toFile().getAbsoluteFile();
        testersFile = resourceDirectory.resolve("devices.csv").toFile().getAbsoluteFile();
        devicesFile = resourceDirectory.resolve("testers.csv").toFile().getAbsoluteFile();
        csvParserService.parseFile(bugsFile);
        csvParserService.parseFile(devicesFile);
        csvParserService.parseFile(testerDeviceFile);
        csvParserService.parseFile(testersFile);
    }

    @Test
    void shouldSuccessfullyFindTestersByCountryAndDevice() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setCountry(Lists.list("US"));
        searchCriteria.setDevice(Lists.list("iPhone 4"));
        List results = searchService.findByCountryAndDevice(searchCriteria);

        TesterWithExperience res1 = (TesterWithExperience) results.get(0);
        TesterWithExperience res2 = (TesterWithExperience) results.get(1);

        assertEquals("Taybin", res1.getFirstName());
        assertEquals("Rutkin", res1.getLastName());
        assertEquals(66L, res1.getBugsExperience());

        assertEquals("Miguel", res2.getFirstName());
        assertEquals("Bautista", res2.getLastName());
        assertEquals(23L, res2.getBugsExperience());
    }

    @Test
    void shouldSuccessfullyFindTestersByDevice() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setCountry(Lists.list("ALL"));
        searchCriteria.setDevice(Lists.list("iPhone 4"));
        List results = searchService.findByCountryAndDevice(searchCriteria);

        TesterWithExperience res1 = (TesterWithExperience) results.get(0);
        TesterWithExperience res2 = (TesterWithExperience) results.get(1);
        TesterWithExperience res3 = (TesterWithExperience) results.get(2);
        TesterWithExperience res4 = (TesterWithExperience) results.get(3);

        assertEquals(4, results.size());
        assertEquals("Taybin", res1.getFirstName());
        assertEquals(66L, res1.getBugsExperience());
        assertEquals("Sean", res2.getFirstName());
        assertEquals(28L, res2.getBugsExperience());
        assertEquals("Miguel", res3.getFirstName());
        assertEquals(23L, res3.getBugsExperience());
        assertEquals("Mingquan", res4.getFirstName());
        assertEquals(21L, res4.getBugsExperience());
    }

    @Test
    void shouldFindAll() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setCountry(Lists.list("ALL"));
        searchCriteria.setDevice(Lists.list("ALL"));
        List results = searchService.findByCountryAndDevice(searchCriteria);
        assertEquals(9, results.size());
    }
}
