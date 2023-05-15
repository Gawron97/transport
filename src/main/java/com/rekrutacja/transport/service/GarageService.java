package com.rekrutacja.transport.service;

import com.rekrutacja.transport.DTO.GarageDTO;
import com.rekrutacja.transport.dao.GarageRepository;
import com.rekrutacja.transport.model.Garage;
import com.rekrutacja.transport.utils.garage.exceptions.GarageError;
import com.rekrutacja.transport.utils.garage.exceptions.GarageNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GarageService {

    private final GarageRepository garageRepository;

    public void addOrUpdateGarage(GarageDTO garageDTO) {
        if(garageDTO.getIdGarage() != null) {
            updateGarage(garageDTO);
        } else {
            addGarage(garageDTO);
        }
    }

    private void addGarage(GarageDTO garageDTO) {
        Garage garage = Garage.of(garageDTO);
        garageRepository.save(garage);
    }

    private void updateGarage(GarageDTO garageDTO) {
        Garage garage = getGarageById(garageDTO.getIdGarage());
        garageRepository.save(garage);
    }

    private Garage getGarageById(Long idGarage) {
        return garageRepository.findById(idGarage).orElseThrow(() ->
                new GarageNotFoundException(GarageError.GARAGE_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<GarageDTO> getGarage(Long idGarage) {

        Garage garage = getGarageById(idGarage);
        return ResponseEntity.ok(GarageDTO.of(garage));

    }

    @Transactional
    public void deleteGarage(Long idGarage) {

        Garage garage = getGarageById(idGarage);
        garage.getTrucks().forEach(truck -> {
            if(truck.getGarage().equals(garage)) {
                truck.setGarage(null);
            }
        });

        garage.getDrivers().forEach(driver -> {
            if(driver.getGarage().equals(garage)) {
                driver.setGarage(null);
            }
        });

        garageRepository.delete(garage);

    }
}
