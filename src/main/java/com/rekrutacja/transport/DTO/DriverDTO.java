package com.rekrutacja.transport.DTO;

import com.rekrutacja.transport.model.Driver;
import com.rekrutacja.transport.model.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Builder
public class DriverDTO {

    private Long idDriver;
    private String name;
    private String surname;
    private Double salary;
    private Integer age;
    private Status status;
    @NotNull
    private Long idGarage;

    public static DriverDTO of(Driver driver) {
        return DriverDTO.builder()
                .idDriver(driver.getIdDriver())
                .name(driver.getName())
                .surname(driver.getSurname())
                .salary(driver.getSalary())
                .age(driver.getAge())
                .status(driver.getStatus())
                .idGarage(driver.getGarage().getIdGarage())
                .build();
    }

}
