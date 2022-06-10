package com.augmentolabs.rmzcorp.realestate.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//We create this class in order to make two fields a primary key. And for this we also do some changes in Floor class.

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FloorId implements Serializable {
    private Long floorNo;
    private Building building;
}
