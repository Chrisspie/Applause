package com.applause.testermatching.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TesterWithExperience {
    private String firstName;
    private String lastName;
    private Long bugsExperience;
}
