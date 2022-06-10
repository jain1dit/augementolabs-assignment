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
public class Locations {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "locations")
    private List<Building> buildings;

    private String locationName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private City city;

    @Override
    public String toString() {
        return "Facilities{" +
                "locationId=" + id +
                ", buildings=" + buildings +
                ", geographicalLocation='" + locationName + '\'' +
                '}';
    }
}
