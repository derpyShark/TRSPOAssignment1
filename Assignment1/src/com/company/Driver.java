package com.company;

import java.util.Date;
import java.util.UUID;

public class Driver {
    final private UUID driverId;
    final private String name;
    final private String surname;
    final private Date dateOfBirth;

    public Driver(String name, String surname, Date dateOfBirth, UUID driverId){
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverId=" + driverId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

}
