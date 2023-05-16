package com.rekrutacja.transport.service;

import com.rekrutacja.transport.DTO.DriverDTO;
import com.rekrutacja.transport.dao.DriverRepository;
import com.rekrutacja.transport.dao.GarageRepository;
import com.rekrutacja.transport.model.Driver;
import com.rekrutacja.transport.model.Garage;
import com.rekrutacja.transport.utils.driver.exceptions.DriverError;
import com.rekrutacja.transport.utils.driver.exceptions.DriverNotFoundException;
import com.rekrutacja.transport.utils.garage.exceptions.GarageError;
import com.rekrutacja.transport.utils.garage.exceptions.GarageNotFoundException;
import com.rekrutacja.transport.utils.generalExceptions.GeneralError;
import com.rekrutacja.transport.utils.generalExceptions.CannotAssignKeyToAddingRecordException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final GarageRepository garageRepository;

    public void addDriver(DriverDTO driverDTO) {

        Garage garage = garageRepository.findById(driverDTO.getIdGarage()).orElseThrow(() ->
                new GarageNotFoundException(GarageError.GARAGE_NOT_FOUND, HttpStatus.NOT_FOUND));

        if(driverDTO.getIdDriver() != null) {
            throw new CannotAssignKeyToAddingRecordException(GeneralError.CANNOT_ASSIGN_KEY_TO_ADDING_RECORD_EXCEPTION,
                    HttpStatus.CONFLICT);
        } else {
            Driver driver = Driver.of(driverDTO);
            driver.setGarage(garage);
            driverRepository.save(driver);
        }

    }

    private Driver getDriverById(Long idDriver) {
        return driverRepository.findById(idDriver).orElseThrow(() ->
                new DriverNotFoundException(DriverError.DRIVER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<DriverDTO> getDriver(Long idDriver) {

        Driver driver = getDriverById(idDriver);
        return ResponseEntity.ok(DriverDTO.of(driver));

    }

    @Transactional
    public void deleteDriver(Long idDriver) {

        Driver driver = getDriverById(idDriver);
        driver.getDeliveries().forEach(delivery -> {
            if(driver.equals(delivery.getDriver())) {
                delivery.setDriver(null);
            }
        });

        driverRepository.delete(driver);
    }

    public void patchDriver(Long idDriver, DriverDTO driverDTO) {

        Driver driver = getDriverById(idDriver);
        if(Objects.nonNull(driverDTO.getSalary())) {
            driver.setSalary(driverDTO.getSalary());
        }
        if(Objects.nonNull(driverDTO.getAge())) {
            driver.setAge(driverDTO.getAge());
        }
        if(Objects.nonNull(driverDTO.getName())) {
            driver.setName(driverDTO.getName());
        }
        if(Objects.nonNull(driverDTO.getSurname())) {
            driver.setSurname(driverDTO.getSurname());
        }
        if(Objects.nonNull(driverDTO.getIdGarage())) {
            Garage garage = garageRepository.findById(driverDTO.getIdGarage()).orElseThrow(() ->
                    new GarageNotFoundException(GarageError.GARAGE_NOT_FOUND, HttpStatus.NOT_FOUND));
            driver.setGarage(garage);
        }

        driverRepository.save(driver);

    }

    public List<DriverDTO> getAllDrivers() {
        return driverRepository.findAll().stream().map(driver -> DriverDTO.of(driver)).toList();
    }
}
