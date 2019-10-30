package com.applause.testermatching.Services;

import com.applause.testermatching.Model.Bug;
import com.applause.testermatching.Model.Device;
import com.applause.testermatching.Model.Tester;
import com.applause.testermatching.Model.TesterDeviceMapping;
import com.applause.testermatching.Repositories.BugRepository;
import com.applause.testermatching.Repositories.DeviceRepository;
import com.applause.testermatching.Repositories.TesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

@Service
public class StartupService {

    @PersistenceContext
    EntityManager em;

    private final CsvParserService csvParserService;
    private final BugRepository bugRepository;
    private final DeviceRepository deviceRepository;
    private final TesterRepository testerRepository;

    @Autowired
    public StartupService(CsvParserService csvParserService, BugRepository bugRepository, DeviceRepository deviceRepository, TesterRepository testerRepository) {
        this.csvParserService = csvParserService;
        this.bugRepository = bugRepository;
        this.deviceRepository = deviceRepository;
        this.testerRepository = testerRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void doSomethingAfterStartup(){
        parseInputFiles();
    }

    public void parseInputFiles() {
        parseAndPersistEntityClasses();
        parseAndPersistMappingFile();
    }

    public void parseAndPersistMappingFile() {
        File file4 = new File("task description/tester_device.csv");
        List<TesterDeviceMapping> testerDeviceMappingList = csvParserService.parseFile(file4);
        for(TesterDeviceMapping testerDeviceMapping : testerDeviceMappingList) {
            Device device = deviceRepository.getOne(testerDeviceMapping.getDeviceId());
            Tester tester = testerRepository.getOne(testerDeviceMapping.getTesterId());
            device.getTester().add(tester);
        }
    }

    public void parseAndPersistEntityClasses() {
        File file = new File("task description/bugs.csv");
        File file2 = new File("task description/devices.csv");
        File file3 = new File("task description/testers.csv");
        List<Bug> bugsList = csvParserService.parseFile(file);
        List<Device> deviceList = csvParserService.parseFile(file2);
        List<Tester> testerList = csvParserService.parseFile(file3);
        bugRepository.saveAll(bugsList);
        deviceRepository.saveAll(deviceList);
        testerRepository.saveAll(testerList);
    }



}
