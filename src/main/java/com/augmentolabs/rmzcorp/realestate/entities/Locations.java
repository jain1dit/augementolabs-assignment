package com.augmentolabs.rmzcorp.realestate.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Locations {

    @Id
    @GeneratedValue
    private long id;

    @JsonManagedReference
    @OneToMany(mappedBy = "locations")
    private List<Building> buildings;

    @Pattern(regexp = "^[A-Za-z]*$")
    private String locationName;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;


}
