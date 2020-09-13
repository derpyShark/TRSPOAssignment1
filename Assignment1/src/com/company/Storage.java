package com.company;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.UUID;

public class Storage {
    final private ArrayList<CargoContainer> storageContainers;
    private StorageCrane storageCrane;
    final private Queue<Truck> trucksToLoadQueue;

    public Storage(){
        storageContainers = new ArrayList<>();
        storageCrane = null;
        trucksToLoadQueue = new LinkedList<>();
    }

    public void setStorageCrane(StorageCrane storageCrane){
        this.storageCrane = storageCrane;
    }

    public StorageCrane getStorageCrane(){
        return storageCrane;
    }

    public Queue<Truck> getTrucksToLoadQueue(){
        return trucksToLoadQueue;
    }

    public ArrayList<CargoContainer> getStorageContainers(){
        return storageContainers;
    }

    public void showContainersInStorage(){
        System.out.println("Showing what is in the storage");
        if(storageContainers.isEmpty()){
            System.out.println("It is empty");
            return;
        }
        for(CargoContainer container : storageContainers){
            System.out.println(container.toString());
        }
    }

    public void addCargoContainer(CargoContainer container){
        storageContainers.add(container);
    }

    public CargoContainer getContainerWithId(UUID containerId){
        for(CargoContainer container : storageContainers){
            if(container.getContainerId() == containerId){
                return container;
            }
        }
        return null;
    }

}
