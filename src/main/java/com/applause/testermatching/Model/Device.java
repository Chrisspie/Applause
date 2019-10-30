package com.applause.testermatching.Model;

import com.univocity.parsers.annotations.Parsed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Device {
    @Parsed
    @Id
    private Long deviceId;
    @Parsed
    private String description;

    @ManyToMany(cascade = {//todo delete
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "tester_device",
            joinColumns = @JoinColumn(name = "device_id"),
            inverseJoinColumns = @JoinColumn(name = "tester_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Tester> tester = new HashSet<>();

}
