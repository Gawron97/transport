package com.rekrutacja.transport.DTO;

import com.rekrutacja.transport.model.Garage;
import com.rekrutacja.transport.model.Truck;
import com.rekrutacja.transport.model.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TruckDTO {

    private Long idTruck;
    private String brand;
    private String model;
    private double capacity;
    private Status status;

    public static TruckDTO of(Truck truck) {
        return TruckDTO.builder()
                .idTruck(truck.getIdTruck())
                .brand(truck.getBrand())
                .model(truck.getModel())
                .capacity(truck.getCapacity())
                .status(truck.getStatus())
                .build();
    }


}
