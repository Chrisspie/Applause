package com.applause.testermatching.Model;

import com.univocity.parsers.annotations.Parsed;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Tester {
    @Parsed
    @Id
    private Long testerId;
    @Parsed
    private String firstName;
    @Parsed
    private String lastName;
    @Parsed
    private String country;
    @Parsed
    private String lastLogin;
}
