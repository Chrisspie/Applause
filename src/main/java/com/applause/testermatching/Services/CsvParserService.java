package com.applause.testermatching.Services;

import com.applause.testermatching.Model.Bug;
import com.applause.testermatching.Model.Device;
import com.applause.testermatching.Model.Tester;
import com.applause.testermatching.Model.TesterDeviceMapping;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CsvParserService {

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
