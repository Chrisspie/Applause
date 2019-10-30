package com.applause.testermatching.Model;

import com.univocity.parsers.annotations.Parsed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

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

    @ManyToMany(mappedBy = "tester")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<Device> devices;
}
