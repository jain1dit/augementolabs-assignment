package com.augmentolabs.rmzcorp.realestate.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

  @Id @GeneratedValue private long id;

  @JsonManagedReference
  @OneToMany(mappedBy = "locations")
  private List<Building> buildings;

  @Pattern(regexp = "^[A-Za-z]*$")
  private String locationName;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private City city;
}
