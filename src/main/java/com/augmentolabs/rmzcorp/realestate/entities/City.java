package com.augmentolabs.rmzcorp.realestate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class City {

    @Id
    @GeneratedValue
    private long id;

    private String country;

    private String state;

    @OneToMany(mappedBy = "city")
    private List<Locations> locations;


    @Override
    public String toString() {
        return "Cities{" +
                "cityId=" + id +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
