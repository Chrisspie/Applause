package com.applause.testermatching.Services;

import com.applause.testermatching.Model.Bug;
import com.applause.testermatching.Model.Device;
import com.applause.testermatching.Model.Tester;
import com.applause.testermatching.Model.TesterDeviceMapping;
import com.applause.testermatching.Repositories.BugRepository;
import com.applause.testermatching.Repositories.DeviceRepository;
import com.applause.testermatching.Repositories.TesterDeviceMappingRepository;
import com.applause.testermatching.Repositories.TesterRepository;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CsvParserService {

    private final DeviceRepository deviceRepository;
    private final TesterRepository testerRepository;
    private final BugRepository bugRepository;
    private final TesterDeviceMappingRepository testerDeviceMappingRepository;

    public CsvParserService(DeviceRepository deviceRepository, TesterRepository testerRepository, BugRepository bugRepository, TesterDeviceMappingRepository testerDeviceMappingRepository) {
        this.deviceRepository = deviceRepository;
        this.testerRepository = testerRepository;
        this.bugRepository = bugRepository;
        this.testerDeviceMappingRepository = testerDeviceMappingRepository;
    }

    void parseAndPersistEntityClasses() {
        File file = new File("task description/bugs.csv");
        File file2 = new File("task description/devices.csv");
        File file3 = new File("task description/testers.csv");
        File file4 = new File("task description/tester_device.csv");

        List<Bug> bugsList = parseFile(file);
        List<Device> deviceList = parseFile(file2);
        List<Tester> testerList = parseFile(file3);
        List<TesterDeviceMapping> testerDeviceMapping = parseFile(file4);
        bugRepository.saveAll(bugsList);
        deviceRepository.saveAll(deviceList);
        testerRepository.saveAll(testerList);
        testerDeviceMappingRepository.saveAll(testerDeviceMapping);
    }

    public List parseFile(File file) {
        String filename = file.getName();
        BeanListProcessor beanListProcessor;
        switch (filename) {
            case "bugs.csv":
                beanListProcessor = parseFileToGivenClass(file, Bug.class);
                break;
            case "devices.csv":
                beanListProcessor = parseFileToGivenClass(file, Device.class);
                break;
            case "testers.csv":
                beanListProcessor = parseFileToGivenClass(file, Tester.class);
                break;
            case "tester_device.csv":
                beanListProcessor = parseFileToGivenClass(file, TesterDeviceMapping.class);
                break;
            default:
                throw new RuntimeException("Unsupported file.");
        }
        return beanListProcessor.getBeans();
    }

    private <T> BeanListProcessor parseFileToGivenClass(File file, Class<T> type) {
        BeanListProcessor<T> beanListProcessor = new BeanListProcessor<>(type);
        CsvParser parser = new CsvParser(createParserSettings(beanListProcessor));
        parser.parse(file);
        return beanListProcessor;
    }

    private CsvParserSettings createParserSettings(BeanListProcessor rowProcessor) {
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.getFormat().setLineSeparator("\n");
        parserSettings.getFormat().setDelimiter(",".charAt(0));
        parserSettings.setProcessor(rowProcessor);
        parserSettings.setEmptyValue(null);
        return parserSettings;
    }


}
