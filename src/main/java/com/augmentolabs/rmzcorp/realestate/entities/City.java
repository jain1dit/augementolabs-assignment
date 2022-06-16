package com.augmentolabs.rmzcorp.realestate.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @Pattern(regexp = "^[A-Za-z]*$")
    private String country;

    @Pattern(regexp = "^[A-Za-z]*$")
    private String state;

    @JsonManagedReference
    @OneToMany(mappedBy = "city")
    private List<Location> locations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getId() == city.getId() && Objects.equals(getName(), city.getName()) && Objects.equals(getCountry(), city.getCountry()) && Objects.equals(getState(), city.getState()) && Objects.equals(getLocations(), city.getLocations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCountry(), getState(), getLocations());
    }
}
