package com.rekrutacja.transport.DTO;

import com.rekrutacja.transport.model.Truck;
import com.rekrutacja.transport.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@Builder
public class TruckDTO {

    private Long idTruck;
    private String brand;
    private String model;
    private Double capacity;
    private Status status;
    @NotNull
    private Long idGarage;

    public static TruckDTO of(Truck truck) {
        return TruckDTO.builder()
                .idTruck(truck.getIdTruck())
                .brand(truck.getBrand())
                .model(truck.getModel())
                .capacity(truck.getCapacity())
                .status(truck.getStatus())
                .idGarage(truck.getGarage().getIdGarage())
                .build();
    }


}
