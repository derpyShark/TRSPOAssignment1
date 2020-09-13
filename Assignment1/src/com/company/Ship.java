package com.company;

import java.util.ArrayList;
import java.util.UUID;

public class Ship {
    final private ArrayList<UUID> shipContainersIdsToUnload;
    final private ArrayList<UUID> shipContainersIdsToKeep;
    final private ArrayList<CargoContainer> shipContainers;

    public void addContainer(CargoContainer addedContainer){
        shipContainers.add(addedContainer);
    }

    public ArrayList<UUID> getShipContainersIdsToUnload(){
        return shipContainersIdsToUnload;
    }

    public CargoContainer getContainer(){
        UUID tempId = shipContainersIdsToUnload.get(shipContainersIdsToUnload.size() - 1);
        shipContainersIdsToUnload.remove(shipContainersIdsToUnload.size() - 1);
        CargoContainer temp = findContainerById(tempId);
        shipContainers.remove(temp);
        return temp;
    }

    public ArrayList<CargoContainer> getShipContainers(){
        return shipContainers;
    }

    public ArrayList<UUID> getShipContainersIdsToKeep(){
        return shipContainersIdsToKeep;
    }

    public Ship(){
        shipContainers = new ArrayList<>();
        shipContainersIdsToKeep = new ArrayList<>();
        shipContainersIdsToUnload = new ArrayList<>();
    }

    private CargoContainer findContainerById(UUID id){
        for(CargoContainer container : shipContainers){
            if(container.getContainerId() == id){
                return container;
            }
        }
        return null;
    }

    public void updateContainersToUnload(){
        for(CargoContainer cargoContainer : shipContainers){
            if(!shipContainersIdsToKeep.contains(cargoContainer.getContainerId())){
                shipContainersIdsToUnload.add(cargoContainer.getContainerId());
            }
        }
    }
}
