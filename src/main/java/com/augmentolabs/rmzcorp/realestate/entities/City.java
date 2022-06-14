package com.augmentolabs.rmzcorp.realestate.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class City {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @Pattern(regexp = "^[A-Za-z]*$")
    private String country;

    @Pattern(regexp = "^[A-Za-z]*$")
    private String state;

    //private boolean active;

    @OneToMany(mappedBy = "city")
    private List<Locations> locations;


    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", locations=" + locations +
                '}';
    }
}
