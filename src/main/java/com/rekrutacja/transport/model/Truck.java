package com.rekrutacja.transport.model;

import com.rekrutacja.transport.DTO.TruckDTO;
import com.rekrutacja.transport.model.enums.Status;
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
    private Double capacity;

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


    public static Truck of(TruckDTO truckDTO) {
        Truck truck = new Truck();
        truck.setBrand(truckDTO.getBrand());
        truck.setModel(truckDTO.getModel());
        truck.setCapacity(truckDTO.getCapacity());
        truck.setStatus(truckDTO.getStatus());
        return truck;
    }

}
