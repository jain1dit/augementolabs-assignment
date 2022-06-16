package com.augmentolabs.rmzcorp.realestate.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

//We create this class in order to make two fields a primary key. And for this we also do some changes in Floor class.

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FloorKey implements Serializable {
    protected long id;
    protected long buildingId;
    //protected Building building;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof FloorKey)) return false;
//        FloorKey floorKey = (FloorKey) o;
//        return getId() == floorKey.getId() && Objects.equals(getBuilding(), floorKey.getBuilding());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getBuilding());
//    }
}
