package com.rekrutacja.transport.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTruck;
    private String brand;
    private String model;
    private double capacity;

    @ManyToOne(fetch = FetchType.EAGER) // do przemyslenia LAZY, razem z przylaczeniem trucka do przesylki wejdzie garage
    @JoinColumn(name = "idGarage")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Garage garage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "truck")
    private List<Delivery> deliveries;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public Truck() {
        deliveries = new ArrayList<>();
    }

}
