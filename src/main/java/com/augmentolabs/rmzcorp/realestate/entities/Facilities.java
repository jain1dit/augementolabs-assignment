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
public class Facilities {

    @Id
    @GeneratedValue
    private long locationId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cities city;

    @OneToMany(mappedBy = "facility")
    private List<Buildings> buildings;

    private String geographicalLocation;

    @Override
    public String toString() {
        return "Facilities{" +
                "locationId=" + locationId +
                ", city=" + city +
                ", buildings=" + buildings +
                ", geographicalLocation='" + geographicalLocation + '\'' +
                '}';
    }
}
