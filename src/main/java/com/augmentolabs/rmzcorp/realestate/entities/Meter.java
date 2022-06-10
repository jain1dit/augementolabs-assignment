package com.augmentolabs.rmzcorp.realestate.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meter {
    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    private MeterType meterType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Zone zone;

    @Override
    public String toString() {
        return "Meter{" +
                "meterId=" + id +
                ", meterType=" + meterType +
                '}';
    }
}
