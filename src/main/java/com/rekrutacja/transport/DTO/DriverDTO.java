package com.rekrutacja.transport.DTO;

import com.rekrutacja.transport.model.Driver;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DriverDTO {

    private Long idDriver;
    private String name;
    private String surname;
    private double salary;
    private int age;
    @NotNull
    private Long idGarage;

    public static DriverDTO of(Driver driver) {
        return DriverDTO.builder()
                .idDriver(driver.getIdDriver())
                .name(driver.getName())
                .surname(driver.getSurname())
                .salary(driver.getSalary())
                .age(driver.getAge())
                .build();
    }

}
