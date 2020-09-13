package com.company;

public class ShipCrane extends Crane {
    private Ship craneShip;

    public Ship getCraneShip() {
        return craneShip;
    }

    public void setCraneShip(Ship craneShip) {
        this.craneShip = craneShip;
    }

    public ShipCrane(CargoContainer cargoContainer, Truck craneTruck,
                     Driver craneOperator, Ship craneShip){
        super(cargoContainer,craneTruck,craneOperator);
        this.craneShip = craneShip;
    }

    public void loadToShip() {
        try{
            checkShip();
            checkCraneContainer();
            checkCraneOperator();
        }
        catch(Exception e){
            System.out.println("Couldn't load to ship: " + e.toString());
        }
        craneShip.addContainer(craneContainer);
        craneContainer = null;
    }

    public void loadFromShip(){
        try{
            checkShip();
            checkShipContainers();
            checkNoCraneContainer();
            checkCraneOperator();
        }
        catch(Exception e){
            System.out.println("Couldn't load from ship: " + e.toString());
        }
        craneContainer = craneShip.getContainer();

    }

    private void checkShip() throws Exception{
        if(craneShip == null){
            throw new Exception("Couldn't perform operation, as there is no ship");
        }
    }

    private void checkShipContainers() throws Exception{
        if(craneShip.getShipContainers().isEmpty()){
            throw new Exception("Couldn't perform operation, as the ship doesn't have any containers");
        }
    }
}
