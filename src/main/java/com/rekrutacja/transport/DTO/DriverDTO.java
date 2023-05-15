package com.rekrutacja.transport.DTO;

import com.rekrutacja.transport.model.Driver;
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
    private Long idGarage;

    public static DriverDTO of(Driver driver) {
        return DriverDTO.builder()
                .name(driver.getName())
                .surname(driver.getSurname())
                .salary(driver.getSalary())
                .age(driver.getAge())
                .build();
    }

}
