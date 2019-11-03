package com.applause.testermatching.Services;

import com.applause.testermatching.Model.Dto.TesterWithExperience;
import com.applause.testermatching.Model.SearchCriteria;
import com.applause.testermatching.Repositories.TesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private final TesterRepository testerRepository;

    @Autowired
    public SearchService(TesterRepository testerRepository) {
        this.testerRepository = testerRepository;
    }

    public List<TesterWithExperience> findByCountryAndDevice(SearchCriteria searchCriteria) {
        List<String> searchCriteriaCountry = searchCriteria.getCountry();
        List<String> searchCriteriaDevice = searchCriteria.getDevice();

        if (searchCriteriaCountry==null || searchCriteriaCountry.contains("ALL")) {
            searchCriteriaCountry = null;
        }

        if (searchCriteriaDevice==null || searchCriteriaDevice.contains("ALL")) {
            searchCriteriaDevice = null;
        }
        List<Object[]> testers = testerRepository.findTestersByCountryAndDeviceDescriptionOrderByBugs(searchCriteriaCountry, searchCriteriaDevice);
        List<TesterWithExperience> testerWithExperienceList = new ArrayList<>();
        for(Object[] tester : testers){
            String name = (String) tester[0];
            String surname = (String) tester[1];
            Long experience = (Long) tester[2];
            testerWithExperienceList.add(new TesterWithExperience(name, surname, experience));
        }
        return testerWithExperienceList;
    }
}
