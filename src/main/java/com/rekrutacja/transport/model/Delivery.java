package com.rekrutacja.transport.model;

import jakarta.persistence.*;
import jakarta.servlet.annotation.HttpConstraint;
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

}
