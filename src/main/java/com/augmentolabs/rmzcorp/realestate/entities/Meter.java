package com.augmentolabs.rmzcorp.realestate.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meter {
    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    @Pattern(regexp = "^[A-Za-z]*$")
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
