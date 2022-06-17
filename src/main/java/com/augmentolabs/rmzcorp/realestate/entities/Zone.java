package com.augmentolabs.rmzcorp.realestate.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Zone {
  @Id @GeneratedValue private long id;

  @Pattern(regexp = "^[A-Za-z]*$")
  private String zoneName;

  @OneToMany(mappedBy = "zone")
  @JsonManagedReference
  private List<Meter> meterType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonBackReference
  private Floor floor;
}
