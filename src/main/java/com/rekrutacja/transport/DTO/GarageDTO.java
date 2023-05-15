package com.rekrutacja.transport.DTO;

import com.rekrutacja.transport.model.Garage;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GarageDTO {

    private Long idGarage;
    private String name;

    public static GarageDTO of(Garage garage) {
        return GarageDTO.builder()
                .idGarage(garage.getIdGarage())
                .name(garage.getName())
                .build();
    }

}
