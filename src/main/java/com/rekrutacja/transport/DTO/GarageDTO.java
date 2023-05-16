package com.rekrutacja.transport.DTO;

import com.rekrutacja.transport.model.Garage;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GarageDTO {

    protected Long idGarage;
    protected String name;

    public GarageDTO() {}

    public static GarageDTO of(Garage garage) {
        return GarageDTO.builder()
                .idGarage(garage.getIdGarage())
                .name(garage.getName())
                .build();
    }

}
