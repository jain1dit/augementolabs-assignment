package com.augmentolabs.rmzcorp.realestate.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JoinColumnsOrFormulas;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(FloorId.class)
public class Floor {

    @NotNull
    @Id
    private long floorNo;

    private int flatsAvailable;

    @OneToMany(mappedBy = "floor")
    private List<Zone> zones;

    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    private Buildings building;

    @Override
    public String toString() {
        return "Floor{" +
                "floorType=" + floorNo +
                ", flatsAvailable=" + flatsAvailable +
                ", zones=" + zones +
                ", building=" + building +
                '}';
    }
}
