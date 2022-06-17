package com.augmentolabs.rmzcorp.realestate.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meter {
  @Id @GeneratedValue private long id;

  @Enumerated(EnumType.STRING)
  private MeterType meterType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonBackReference
  private Zone zone;
}
