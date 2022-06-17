package com.augmentolabs.rmzcorp.realestate.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Building {
  @Id @GeneratedValue private long id;

  private String name;

  @JsonManagedReference
  @OneToMany(mappedBy = "building")
  private List<Floor> floors;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  private Location locations;
}
