package com.applause.testermatching.Model;

import com.univocity.parsers.annotations.Parsed;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Bug {
    @Parsed
    @Id
    private Long bugId;
    @Parsed
    private Long deviceId;
    @Parsed
    private Long testerId;
}
