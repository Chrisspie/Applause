package com.applause.testermatching.Model;

import com.univocity.parsers.annotations.Parsed;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class TesterDeviceMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Parsed
    private Long testerId;
    @Parsed
    private Long deviceId;
}
