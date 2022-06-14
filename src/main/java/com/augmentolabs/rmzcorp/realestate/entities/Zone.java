package com.augmentolabs.rmzcorp.realestate.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Zone {
    @Id
    @GeneratedValue
    private long id;

    @Pattern(regexp = "^[A-Za-z]*$")
    private String zoneName;

    @OneToMany(mappedBy = "zone")
    private List<Meter> meterType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Floor floor;

    @Override
    public String toString() {
        return "Zone{" +
                "zoneId=" + id +
                ", specifyZone='" + zoneName + '\'' +
                ", meterType='" + meterType + '\'' +
                '}';
    }
}
