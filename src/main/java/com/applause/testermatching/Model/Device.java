package com.applause.testermatching.Model;

import com.univocity.parsers.annotations.Parsed;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Device {
    @Parsed
    @Id
    private Long deviceId;
    @Parsed
    private String description;
}
