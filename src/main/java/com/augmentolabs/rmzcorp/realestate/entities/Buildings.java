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
public class Buildings {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Facilities facility;

    @OneToMany(mappedBy = "building")
    private List<Floor> floors;

    @Override
    public String toString() {
        return "Buildings{" +
                "buildingId=" + id +
                ", facility=" + facility +
                ", floors=" + floors +
                '}';
    }
}
