package com.augmentolabs.rmzcorp.realestate.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zone {
    @Id
    @GeneratedValue
    private long zoneId;

    private String specifyZone;

    @ManyToOne(fetch = FetchType.LAZY)
    private Floor floor;

    @OneToMany(mappedBy = "zone")
    private List<Meter> meterType;

    @Override
    public String toString() {
        return "Zone{" +
                "zoneId=" + zoneId +
                ", specifyZone='" + specifyZone + '\'' +
                ", meterType='" + meterType + '\'' +
                '}';
    }
}
