package com.applause.testermatching.Controllers;

import com.applause.testermatching.Model.Dto.TesterWithExperience;
import com.applause.testermatching.Model.SearchCriteria;
import com.applause.testermatching.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public List<TesterWithExperience> search(@RequestBody SearchCriteria searchCriteria) {
        return searchService.findByCountryAndDevice(searchCriteria);
    }
}
