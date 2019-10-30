package com.applause.testermatching.Model;

import com.univocity.parsers.annotations.Parsed;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TesterDeviceMapping {
    @Parsed
    private Long testerId;
    @Parsed
    private Long deviceId;
}
