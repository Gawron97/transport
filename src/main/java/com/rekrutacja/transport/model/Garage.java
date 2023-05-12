package com.rekrutacja.transport.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGarage;
    private String name;

    @OneToMany(mappedBy = "garage", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Truck> trucks;

    @OneToMany(mappedBy = "garage", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Driver> drivers;

    public Garage() {
        trucks = new ArrayList<>();
        drivers = new ArrayList<>();
    }

    public void addTruck(Truck truck) {
        trucks.add(truck);
        truck.setGarage(this);
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
        driver.setGarage(this);
    }

    public void removeTruck(Truck truck) {
        trucks.remove(truck);
        truck.setGarage(null);
    }

    public void removeDriver(Driver driver) {
        drivers.remove(driver);
        driver.setGarage(null);
    }
}
