package com.rekrutacja.transport.model;

import com.rekrutacja.transport.model.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelivery;
    private String itemName;
    private double weight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDriver")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Driver driver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTruck")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Truck truck;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;

    public void addTruck(Truck truck) {
        this.truck = truck;
        truck.getDeliveries().add(this);
    }

    public void addDriver(Driver driver) {
        this.driver = driver;
        driver.getDeliveries().add(this);
    }

    public void removeTruck(Truck truck) {
        this.truck = null;
        truck.getDeliveries().remove(this);
    }

    public void removeDriver(Driver driver) {
        this.driver = null;
        driver.getDeliveries().remove(this);
    }

}
