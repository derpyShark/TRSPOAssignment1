package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class ShipCraneManager {
    private ArrayList<ShipCrane> navalCranes;
    final private Queue<Ship> shipQueue;

    public ShipCraneManager(){
        navalCranes = new ArrayList<>();
        shipQueue = new LinkedList<>();
    }

    public ArrayList<ShipCrane> getNavalCranes(){
        return navalCranes;
    }

    public void setNavalCranes(ArrayList<ShipCrane> navalCranes){
        this.navalCranes = navalCranes;
    }

    public void moveShipsIntoCranes(){
        for(ShipCrane shipCrane : navalCranes){
            if(shipQueue.isEmpty()){
                break;
            }
            if(shipCrane.getCraneShip() == null){
                shipCrane.setCraneShip(shipQueue.poll());
            }
        }
    }

    public void fillShipQueue(ArrayList<Ship> ships){
        shipQueue.addAll(ships);
    }

    public void loadFromShipsToTrucks(){
        for(ShipCrane crane : navalCranes){
            boolean areContainersToUnload = !crane.getCraneShip().getShipContainersIdsToUnload().isEmpty();
            if( areContainersToUnload && crane.getCraneTruck() != null){
                crane.loadFromShip();
                crane.loadToTruck();
            }
        }
    }

    public void loadFromTrucksToShips(){
        for(ShipCrane crane : navalCranes){
            if(crane.getCraneTruck() != null){
                crane.loadFromTruck();
                crane.loadToShip();
            }
        }
    }
}
