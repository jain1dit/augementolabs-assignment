package com.augmentolabs.rmzcorp.realestate.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(FloorKey.class)
public class Floor implements Serializable {

  @NotNull
  @Id
  @GeneratedValue
  @Column(name = "id")
  private long id;

  @Id
  @Column(name = "building_id")
  private long buildingId;

  private int floorNumber;

  @JsonManagedReference
  @OneToMany(mappedBy = "floor")
  private List<Zone> zones;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "building_id", insertable = false, updatable = false)
  @JsonBackReference
  private Building building;
}
