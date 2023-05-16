package com.rekrutacja.transport.DTO;

import com.rekrutacja.transport.model.Garage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class GarageWithAssociationsDTO extends GarageDTO {

    private List<TruckDTO> truckDTOList;
    private List<DriverDTO> driverDTOList;

    public static GarageWithAssociationsDTO of(Garage garage) {
        return GarageWithAssociationsDTO.builder()
                .idGarage(garage.getIdGarage())
                .name(garage.getName())
                .truckDTOList(garage.getTrucks().stream().map(truck -> TruckDTO.of(truck)).toList())
                .driverDTOList(garage.getDrivers().stream().map(driver -> DriverDTO.of(driver)).toList())
                .build();
    }

}
