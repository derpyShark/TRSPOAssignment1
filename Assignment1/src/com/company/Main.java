package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        cal.set(1960,Calendar.FEBRUARY,1);
        Date early = cal.getTime();
        cal.set(2000,Calendar.FEBRUARY,1);
        Date late = cal.getTime();

        RandomUtility randomUtility = new RandomUtility();
        RandomBoundries randomBoundries = new RandomBoundries(0,3000,early,late);
        GeneratedAmount generatedAmount = new GeneratedAmount(5,2,15);

        WorkShift work = randomUtility.getRandomWorkShift(generatedAmount,randomBoundries);
        displayIncomingTrucks(work.getIncomingTrucks());
        displayShipInfo(work.getIncomingShips());

        Driver shipCraneDriver1 = randomUtility.getRandomDriver(early,late);
        Driver shipCraneDriver2 = randomUtility.getRandomDriver(early,late);
        Driver storageCraneDriver = randomUtility.getRandomDriver(early,late);

        ShipCrane shipCrane1 = new ShipCrane(null,null,
                shipCraneDriver1,null);
        ShipCrane shipCrane2 = new ShipCrane(null,null,
                shipCraneDriver2,null);
        ArrayList<ShipCrane> shipCranes = new ArrayList<>();
        shipCranes.add(shipCrane1);
        shipCranes.add(shipCrane2);

        Truck portTruck1 = randomUtility.getRandomTruck(0, 3000,early,late);
        Truck portTruck2 = randomUtility.getRandomTruck(0, 3000,early,late);
        Truck portTruck3 = randomUtility.getRandomTruck(0, 3000,early,late);


        ArrayList<Truck> portTrucks = new ArrayList<>();
        portTrucks.add(portTruck1);
        portTrucks.add(portTruck2);
        portTrucks.add(portTruck3);
        for(Truck truck : portTrucks){
            truck.setContainer(null);
        }

        Storage storage = new Storage();
        StorageCrane storageCrane = new StorageCrane(null,null,
            storageCraneDriver, storage);

        storage.setStorageCrane(storageCrane);

        ShipCraneManager shipCraneManager = new ShipCraneManager();
        shipCraneManager.setNavalCranes(shipCranes);
        shipCraneManager.fillShipQueue(work.getIncomingShips());

        System.out.println("A new work shift has started and the ships have moved into cranes");

        shipCraneManager.moveShipsIntoCranes();
        PortTruckManager portTruckManager = new PortTruckManager(portTrucks,shipCranes,storage);
        RoutineManager routineManager = new RoutineManager(portTruckManager,shipCraneManager);

        System.out.println("Starting to unload the ships to the storage");

        routineManager.performShipUnloadingRoutine();

        System.out.println("\nStatus on the ships and the storage after:\n");

        displayShipInfo(work.getIncomingShips());
        storage.showContainersInStorage();

        System.out.println("\nStarting to unload and load the incoming trucks from the storage\n");

        routineManager.performIncomingTruckHandlingRoutine(work.getIncomingTrucks());

        System.out.println("\nStatus on the storage and the trucks after\n");

        storage.showContainersInStorage();

        displayIncomingTrucks(work.getIncomingTrucks());

        System.out.println("\nStarting to load the ships from the storage\n");

        routineManager.performShipLoadingRoutine();

        System.out.println("\nDisplaying the result at the end of the shift\n");

        displayShipInfo(work.getIncomingShips());

        storage.showContainersInStorage();

        displayIncomingTrucks(work.getIncomingTrucks());

    }
    static private void displayShipInfo(ArrayList<Ship> ships){
        System.out.println("Checking ship info");
        for(Ship ship : ships){
            System.out.println("The container info on one of the ships");
            System.out.println("Containers onboard");
            for(CargoContainer container : ship.getShipContainers()){
                System.out.println(container.getContainerId());
            }
            System.out.println("Containers we want");
            for(UUID id : ship.getShipContainersIdsToKeep()){
                System.out.println(id);
            }
        }
    }

    static private void displayIncomingTrucks(ArrayList<Truck> trucks){
        System.out.println("We have incoming trucks");
        for(Truck tr : trucks){
            System.out.println(tr.toString());
        }
    }
}
