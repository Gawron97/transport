package com.rekrutacja.transport.model;

import com.rekrutacja.transport.DTO.DriverDTO;
import com.rekrutacja.transport.model.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDriver;
    private String name;
    private String surname;
    private double salary;
    private int age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idGarage")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Garage garage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
    private List<Delivery> deliveries;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public static Driver of(DriverDTO driverDTO) {
        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setSurname(driverDTO.getSurname());
        driver.setSalary(driverDTO.getSalary());
        driver.setAge(driverDTO.getAge());
        return driver;
    }

}
