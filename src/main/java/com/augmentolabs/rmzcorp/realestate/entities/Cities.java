package com.augmentolabs.rmzcorp.realestate.entities;

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
public class Cities {

    @Id
    @GeneratedValue
    private long id;

    private String country;

    private String state;

    @OneToMany(mappedBy = "city")
    private List<Facilities> facility;


    @Override
    public String toString() {
        return "Cities{" +
                "cityId=" + id +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
