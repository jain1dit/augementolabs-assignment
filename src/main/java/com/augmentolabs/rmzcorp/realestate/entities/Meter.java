package com.augmentolabs.rmzcorp.realestate.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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
    private MeterType meterType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Zone zone;


}
