package com.rekrutacja.transport.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDriver;
    private String name;
    private String surname;
    private double salary;
    private int age;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
    private List<Delivery> deliveries;

}
