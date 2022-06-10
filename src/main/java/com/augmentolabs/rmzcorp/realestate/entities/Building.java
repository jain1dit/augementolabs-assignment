package com.augmentolabs.rmzcorp.realestate.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Building {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "building")
    private List<Floor> floors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Locations locations;

    @Override
    public String toString() {
        return "Buildings{" +
                "buildingId=" + id +
                ", floors=" + floors +
                '}';
    }
}
