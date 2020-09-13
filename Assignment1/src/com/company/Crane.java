package com.company;

public abstract class Crane {

    protected CargoContainer craneContainer;
    protected Truck craneTruck;
    protected Driver craneOperator;

    public Crane() {
        craneContainer = null;
        craneTruck = null;
        craneOperator = null;
    }

    public Crane(CargoContainer craneContainer, Truck craneTruck, Driver craneOperator){
        this.craneContainer = craneContainer;
        this.craneTruck = craneTruck;
        this.craneOperator = craneOperator;
    }

    public Truck getCraneTruck() {
        return craneTruck;
    }

    public void setCraneTruck(Truck craneTruck) {
        this.craneTruck = craneTruck;
    }

    public void loadToTruck(){
        try{
            checkTruck();
            checkNoTruckContainer();
            checkCraneContainer();
            checkCraneOperator();
        }
        catch (Exception e){
            System.out.println("Couldn't load to truck: " + e.toString());
        }
        craneTruck.setContainer(craneContainer);
        craneContainer = null;
        craneTruck = null;
    }


    public void loadFromTruck(){
        try{
            checkTruck();
            checkTruckContainer();
            checkNoCraneContainer();
            checkCraneOperator();
        }
        catch (Exception e){
            System.out.println("Couldn't load from truck: " + e.toString());
        }
        craneContainer = craneTruck.getContainer();
        craneTruck.setContainer(null);
        craneTruck = null;
    }



    protected void checkTruck() throws Exception {
        if (craneTruck == null) {
            throw new Exception("Couldn't perform operation, as there is no truck");
        }
    }

    protected void checkNoTruckContainer() throws Exception {
        if (craneTruck.getContainer() != null) {
            throw new Exception("Couldn't perform operation, as already has a container");
        }
    }

    protected void checkCraneContainer() throws Exception {
        if (craneContainer == null) {
            throw new Exception("Couldn't perform operation, as there is no container to load");
        }
    }

    protected void checkCraneOperator() throws Exception {
        if (craneOperator == null) {
            throw new Exception("Couldn't perform operation, as there is no crane operator");
        }
    }

    protected void checkNoCraneContainer() throws Exception{
        if(craneContainer != null){
            throw new Exception("Couldn't perform operation, as there already is a loaded container");
        }
    }

    protected void checkTruckContainer() throws Exception{
        if(craneTruck.getContainer() == null){
            throw new Exception("Couldn't perform operation, as the truck doesn't have a container");
        }
    }


}
