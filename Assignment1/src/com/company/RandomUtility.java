package com.company;

import java.util.*;

public class RandomUtility {

    Random rand;

    public RandomUtility(){
        rand = new Random();
    }

    public Name getRandomName(){
        return Name.values()[rand.nextInt(Name.values().length)];
    }

    public Surname getRandomSurname(){
        return Surname.values()[rand.nextInt(Surname.values().length)];
    }

    public TruckModel getRandomTruckModel(){
        return TruckModel.values()[rand.nextInt(TruckModel.values().length)];
    }

    public Date getRandomDate(Date lowerBound, Date upperBound){
        long range = (long)(rand.nextDouble()*(upperBound.getTime() - lowerBound.getTime()));
        return new Date(range + lowerBound.getTime());
    }

    public Driver getRandomDriver(Date earlyDate, Date laterDate){
        return new Driver(getRandomName().toString(),
                getRandomSurname().toString(),
                getRandomDate(earlyDate, laterDate),
                UUID.randomUUID());
    }

    public CargoContainer getRandomCargoContainer(int lowWeight, int upperWeight){
        return new CargoContainer(UUID.randomUUID(),
                (int)(rand.nextDouble() * (upperWeight - lowWeight) + lowWeight),
                UUID.randomUUID());
    }

    public Truck getRandomTruck(int lowMass, int upperMass, Date earlyDate, Date laterDate){
        return new Truck(getRandomCargoContainer(lowMass, upperMass),
                getRandomDriver(earlyDate, laterDate),
                getRandomTruckModel(),
                UUID.randomUUID(),
                UUID.randomUUID());
    }

    public WorkShift getRandomWorkShift(GeneratedAmount generatedAmount,
                                        RandomBoundries randomBoundries){

        int lowMass = randomBoundries.low;
        int upperMass = randomBoundries.high;
        Date earlyDate = randomBoundries.earlyDate;
        Date laterDate = randomBoundries.lateDate;
        int numberOfTrucks = generatedAmount.numberOfTrucks;
        int numberOfContainers = generatedAmount.numberOfContainers;
        int numberOfShips = generatedAmount.numberOfShips;

        ArrayList<CargoContainer> incomingCargoContainers = new ArrayList<>();

        for(int i =0; i < numberOfContainers; i++){
            incomingCargoContainers.add(getRandomCargoContainer(lowMass, upperMass));
        }

        ArrayList<CargoContainer> incomingTruckContainers = new ArrayList<>
                (incomingCargoContainers.subList(0,numberOfTrucks));
        ArrayList<CargoContainer> incomingShipContainers = new ArrayList<>
                (incomingCargoContainers.subList(numberOfTrucks,numberOfContainers ));

        ArrayList<UUID> truckRequestedContainers = new ArrayList<>();
        ArrayList<UUID> shipRequestedContainers = new ArrayList<>();

        for(int i = 0; i < numberOfTrucks;i++){
            truckRequestedContainers.add(incomingShipContainers.get(i).getContainerId());
            shipRequestedContainers.add(incomingTruckContainers.get(i).getContainerId());
        }
        for(int i = numberOfTrucks; i < numberOfContainers - numberOfTrucks;i++){
            shipRequestedContainers.add(incomingShipContainers.get(i).getContainerId());
        }
        Collections.shuffle(truckRequestedContainers);
        Collections.shuffle(shipRequestedContainers);

        //getting an arraylist of trucks
        ArrayList<Truck> incomingTrucks = new ArrayList<>();
        for(int i = 0 ; i < numberOfTrucks;i++){
            incomingTrucks.add(getRandomTruck(lowMass,upperMass,earlyDate,laterDate));
            incomingTrucks.get(i).setContainer(incomingTruckContainers.get(i));
            incomingTrucks.get(i).setContainerToLoad(truckRequestedContainers.get(i));
        }

        //getting ar arraylist of ships

        ArrayList<Ship> incomingShips = new ArrayList<>();
        for(int i =0; i<numberOfShips;i++){
            incomingShips.add(new Ship());
        }

        for(int i = 0; i < numberOfContainers - numberOfTrucks;i++){
            incomingShips.get(i%numberOfShips).getShipContainers().add(incomingShipContainers.get(i));
            incomingShips.get(i%numberOfShips).getShipContainersIdsToKeep().add(shipRequestedContainers.get(i));
        }

        for(Ship ship : incomingShips){
            ship.updateContainersToUnload();
        }
        return new WorkShift(incomingTrucks, incomingShips);
    }
}
