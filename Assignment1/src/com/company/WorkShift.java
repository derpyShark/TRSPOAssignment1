package com.company;

import java.util.ArrayList;

public class WorkShift {

    final private ArrayList<Truck> incomingTrucks;
    final private ArrayList<Ship> incomingShips;

    public WorkShift(ArrayList<Truck> incomingTrucks, ArrayList<Ship> incomingShips){
        this.incomingTrucks = incomingTrucks;
        this.incomingShips = incomingShips;
    }

    public ArrayList<Truck> getIncomingTrucks(){
        return incomingTrucks;
    }

    public ArrayList<Ship> getIncomingShips(){
        return incomingShips;
    }
}
