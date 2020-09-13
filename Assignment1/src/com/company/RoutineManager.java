package com.company;

import java.util.ArrayList;

public class RoutineManager {

    final private PortTruckManager portTruckManager;
    final private ShipCraneManager shipCraneManager;

    public RoutineManager(PortTruckManager portTruckManager, ShipCraneManager shipCraneManager){
        this.portTruckManager = portTruckManager;
        this.shipCraneManager = shipCraneManager;
    }

    public void performShipUnloadingRoutine(){
        boolean nothingToUnload = false;
        while(!nothingToUnload){
            nothingToUnload = true;
            for(ShipCrane crane : shipCraneManager.getNavalCranes()){
                if(!crane.getCraneShip().getShipContainersIdsToUnload().isEmpty()){
                    portTruckManager.moveTrucksToShipCraneForUnloading();
                    shipCraneManager.loadFromShipsToTrucks();
                    portTruckManager.moveLoadedTrucksToStorage();
                    portTruckManager.unloadTrucksToStorage();
                    nothingToUnload = false;
                }
            }
        }
    }

    public void performIncomingTruckHandlingRoutine(ArrayList<Truck> incomingTrucks){
        for(Truck truck : incomingTrucks){
            portTruckManager.handleIncomingTruck(truck);
        }
    }

    public void performShipLoadingRoutine(){
        boolean needToLoad = true;
        while(needToLoad){
            needToLoad = false;
            for(ShipCrane crane : shipCraneManager.getNavalCranes()){
                int amountOnboard = crane.getCraneShip().getShipContainers().size();
                int amountRequested = crane.getCraneShip().getShipContainersIdsToKeep().size();
                if(amountOnboard != amountRequested){
                    needToLoad = true;
                    break;
                }
            }
            portTruckManager.moveUnloadedTrucksToStorage();
            portTruckManager.loadTrucksFromStorage();
            portTruckManager.moveLoadedTrucksToShipCranes();
            shipCraneManager.loadFromTrucksToShips();
        }
    }

}
