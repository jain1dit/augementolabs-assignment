package com.augmentolabs.rmzcorp.realestate.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import java.util.List;


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



}
