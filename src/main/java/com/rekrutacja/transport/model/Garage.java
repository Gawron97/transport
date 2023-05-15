package com.rekrutacja.transport.model;

import com.rekrutacja.transport.DTO.GarageDTO;
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

    @OneToMany(mappedBy = "garage", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Truck> trucks;

    @OneToMany(mappedBy = "garage", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Driver> drivers;

    public Garage() {
        trucks = new ArrayList<>();
        drivers = new ArrayList<>();
    }

    public static Garage of(GarageDTO garageDTO) {
        Garage garage = new Garage();
        garage.setName(garage.getName());
        return garage;
    }
}
