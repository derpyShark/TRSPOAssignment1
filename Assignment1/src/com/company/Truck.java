package com.company;

import java.util.UUID;

public class Truck {
    private CargoContainer container;
    final private Driver truckDriver;
    final private TruckModel truckModel;
    final private UUID truckId;
    private UUID containerToLoad;

    public CargoContainer getContainer(){
        return container;
    }

    public void setContainer(CargoContainer container){
        this.container = container;
    }

    public void setContainerToLoad(UUID containerToLoad){
        this.containerToLoad = containerToLoad;
    }

    public UUID getContainerToLoad(){
        return containerToLoad;
    }

    public Truck(CargoContainer container, Driver truckDriver,
                 TruckModel truckModel, UUID truckId, UUID containerToLoad){
        this.container = container;
        this.truckDriver = truckDriver;
        this.truckModel = truckModel;
        this.truckId = truckId;
        this.containerToLoad = containerToLoad;
    }

    public void driveToCrane(Crane crane){
        try{
            checkCraneDriver();
            checkNoCraneTruck(crane);
        }
        catch(Exception e){
            System.out.println("Couldn't drive to crane: " + e.toString());
        }
        crane.setCraneTruck(this);

    }

    private void checkCraneDriver() throws Exception{
        if(truckDriver == null){
            throw new Exception("Truck cannot drive to crane, as there is no driver");
        }
    }

    private void checkNoCraneTruck(Crane crane) throws Exception{
        if(crane.getCraneTruck() != null){
            throw new Exception("Truck cannot drive to crane, as the crane is already occupied");
        }
    }

    @Override
    public String toString() {
        return "Truck{" +
                "truckId=" + truckId +
                ", truckModel=" + truckModel +
                ", truckDriver=" + truckDriver +
                ", container=" + container +
                ", containerToLoad=" + containerToLoad +
                '}';
    }



}
