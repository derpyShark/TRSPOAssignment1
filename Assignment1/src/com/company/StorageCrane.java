package com.company;

import java.util.UUID;

public class StorageCrane extends Crane{

    Storage craneStorage;

    public StorageCrane(CargoContainer cargoContainer, Truck craneTruck,
                        Driver craneOperator, Storage craneStorage){
        super(cargoContainer,craneTruck,craneOperator);
        this.craneStorage = craneStorage;
    }

    public void loadToStorage(){

        try{
            checkStorage();
            checkCraneOperator();
            checkCraneContainer();
        }
        catch (Exception e){
            System.out.println("Couldn't load to storage: " + e.toString());
        }
        craneStorage.addCargoContainer(craneContainer);
        craneContainer = null;
    }

    public void loadSpecificFromStorage(UUID containerIdToLoad){
        try{
            checkStorage();
            checkCraneOperator();
            checkNoCraneContainer();
            checkContainerExistence(containerIdToLoad);
        }
        catch(Exception e){
            System.out.println("Couldn't load from storage: " + e.toString());
        }
        CargoContainer container = craneStorage.getContainerWithId(containerIdToLoad);
        craneContainer = container;
        craneStorage.getStorageContainers().remove(container);
    }

    public void loadAnyFromStorage(){
        try{
            checkStorage();
            checkCraneOperator();
            checkNoCraneContainer();
            checkStorageContainers();
        }
        catch(Exception e){
            System.out.println("Couldn't load from storage: " + e.toString());
        }
        CargoContainer container = craneStorage.getStorageContainers().get(0);
        craneContainer = container;
        craneStorage.getStorageContainers().remove(container);
    }

    private void checkStorageContainers() throws Exception {
        if(craneStorage.getStorageContainers().isEmpty()){
            throw new Exception("Couldn't perform operation, as storage is empty");
        }
    }

    private void checkStorage() throws Exception {
        if(craneStorage == null){
            throw new Exception("Couldn't perform operation, as there is no storage");
        }
    }

    private void checkContainerExistence(UUID containerId) throws Exception{
        if(craneStorage.getContainerWithId(containerId) == null)
        throw new Exception("Couldn't perform operation, as there is no container with such an ID");
    }
}
