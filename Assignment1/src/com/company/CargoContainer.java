package com.company;

import java.util.UUID;

public class CargoContainer {
    final private UUID cargoId;
    final private float cargoWeight;
    final private UUID containerId;

    public UUID getContainerId(){
        return containerId;
    }

    public CargoContainer(UUID cargoId,int cargoWeight, UUID containerId){
        this.cargoId = cargoId;
        this.containerId = containerId;
        this.cargoWeight = cargoWeight;
    }

    @Override
    public String toString() {
        return "CargoContainer{" +
                "cargoId=" + cargoId +
                ", cargoWeight=" + cargoWeight +
                ", containerId=" + containerId +
                '}';
    }
}
