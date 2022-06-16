package com.augmentolabs.rmzcorp.realestate.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(FloorId.class)
public class Floor {

    @NotNull
    @Id
    @GeneratedValue
    @Column(name= "no")
    private long floorNo;

    private int flatsAvailable;

    @JsonManagedReference
    @OneToMany(mappedBy = "floor")
    private List<Zone> zones;

    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    @JsonBackReference
    private Building building;


}
