package com.company;


import java.util.ArrayList;
import java.util.UUID;


public class PortTruckManager {

    final private ArrayList<Truck> portTrucks;
    final private ArrayList<ShipCrane> shipCranes;
    final private Storage storage;

    public PortTruckManager(ArrayList<Truck> portTrucks,
                            ArrayList<ShipCrane> shipCranes, Storage storage){
        this.portTrucks = portTrucks;
        this.shipCranes = shipCranes;
        this.storage= storage;
    }

    public void moveTrucksToShipCraneForUnloading(){
        int k = 0;
        for (ShipCrane shipCrane : shipCranes) {
            if (shipCrane.getCraneShip() != null) {
                boolean thereAreContainers = !shipCrane.getCraneShip().getShipContainersIdsToUnload().isEmpty();
                if (thereAreContainers) {
                    portTrucks.get(k).driveToCrane(shipCrane);
                    k++;
                    if (k > portTrucks.size()) {
                        break;
                    }
                }
            }
        }
    }

    public void moveLoadedTrucksToStorage(){
        for(Truck truck : portTrucks){
            if(truck.getContainer() != null){
                storage.getTrucksToLoadQueue().add(truck);
            }
        }
    }

    public void moveUnloadedTrucksToStorage(){
        for(Truck truck : portTrucks){
            if(truck.getContainer() == null){
                storage.getTrucksToLoadQueue().add(truck);
            }
        }
    }

    public void loadTrucksFromStorage(){
       while(!storage.getTrucksToLoadQueue().isEmpty() &&
               !storage.getStorageContainers().isEmpty()){

           Truck truck = storage.getTrucksToLoadQueue().poll();
           truck.driveToCrane(storage.getStorageCrane());
           storage.getStorageCrane().loadAnyFromStorage();
           storage.getStorageCrane().loadToTruck();
       }
    }

    public void moveLoadedTrucksToShipCranes(){

        for (ShipCrane shipCrane : shipCranes) {
            if (shipCrane.getCraneShip() != null) {
                for(Truck truck : portTrucks){
                    if(truck.getContainer() != null){

                        ArrayList<UUID> shipIdList = shipCrane.getCraneShip().getShipContainersIdsToKeep();
                        UUID truckContainerId = truck.getContainer().getContainerId();
                        boolean matchingContainer = shipIdList.contains(truckContainerId);

                        if(matchingContainer && shipCrane.getCraneTruck() == null){
                            truck.driveToCrane(shipCrane);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void handleIncomingTruck(Truck truck){
        truck.driveToCrane(storage.getStorageCrane());
        storage.getStorageCrane().loadFromTruck();
        truck.driveToCrane(storage.getStorageCrane());
        storage.getStorageCrane().loadToStorage();
        storage.getStorageCrane().loadSpecificFromStorage(truck.getContainerToLoad());
        storage.getStorageCrane().loadToTruck();
    }

    public void unloadTrucksToStorage(){
        while(!storage.getTrucksToLoadQueue().isEmpty()){
            Truck truck = storage.getTrucksToLoadQueue().poll();
            truck.driveToCrane(storage.getStorageCrane());
            storage.getStorageCrane().loadFromTruck();
            storage.getStorageCrane().loadToStorage();
            storage.getStorageCrane().setCraneTruck(null);
        }
    }

}
