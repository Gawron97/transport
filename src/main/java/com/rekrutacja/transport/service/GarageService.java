package com.rekrutacja.transport.service;

import com.rekrutacja.transport.DTO.GarageDTO;
import com.rekrutacja.transport.DTO.GarageWithAssociationsDTO;
import com.rekrutacja.transport.dao.GarageRepository;
import com.rekrutacja.transport.model.Garage;
import com.rekrutacja.transport.utils.garage.exceptions.CannotDeleteGarageWithDriversException;
import com.rekrutacja.transport.utils.garage.exceptions.CannotDeleteGarageWithTrucksException;
import com.rekrutacja.transport.utils.garage.exceptions.GarageError;
import com.rekrutacja.transport.utils.garage.exceptions.GarageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GarageService {

    private final GarageRepository garageRepository;

    public void addGarage(GarageDTO garageDTO) {
        Garage garage = Garage.of(garageDTO);
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

    public ResponseEntity<GarageWithAssociationsDTO> getGarageWithAssociations(Long idGarage) {

        Garage garage = getGarageById(idGarage);
        return ResponseEntity.ok(GarageWithAssociationsDTO.of(garage));

    }

    public List<GarageDTO> getAllGarages() {
        return garageRepository.findAll().stream().map(garage -> GarageDTO.of(garage)).toList();
    }

    public List<GarageWithAssociationsDTO> getAllGaragesWithAssociations() {
        return garageRepository.findAll().stream().map(garage -> GarageWithAssociationsDTO.of(garage)).toList();
    }

    public void deleteGarage(Long idGarage) {

        Garage garage = getGarageById(idGarage);

        if(garage.getTrucks().size() > 0) {
            throw new CannotDeleteGarageWithTrucksException(GarageError.CANNOT_DELETE_GARAGE_WITH_TRUCKS_INSIDE, HttpStatus.CONFLICT);
        }
        if(garage.getDrivers().size() > 0) {
            throw new CannotDeleteGarageWithDriversException(GarageError.CANNOT_DELETE_GARAGE_WITH_DRIVERS_INSIDE, HttpStatus.CONFLICT);
        }

        garageRepository.delete(garage);

    }
}
