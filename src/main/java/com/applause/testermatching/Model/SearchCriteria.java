package com.applause.testermatching.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchCriteria {
    List<String> country;
    List<String> device;
}
