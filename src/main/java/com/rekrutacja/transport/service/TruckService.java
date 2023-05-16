package com.rekrutacja.transport.service;


import com.rekrutacja.transport.DTO.TruckDTO;
import com.rekrutacja.transport.dao.GarageRepository;
import com.rekrutacja.transport.dao.TruckRepository;
import com.rekrutacja.transport.model.Garage;
import com.rekrutacja.transport.model.Truck;
import com.rekrutacja.transport.utils.garage.exceptions.GarageError;
import com.rekrutacja.transport.utils.garage.exceptions.GarageNotFoundException;
import com.rekrutacja.transport.utils.generalExceptions.GeneralError;
import com.rekrutacja.transport.utils.generalExceptions.CannotAssignKeyToAddingRecordException;
import com.rekrutacja.transport.utils.trucks.exceptions.TruckError;
import com.rekrutacja.transport.utils.trucks.exceptions.TruckNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TruckService {

    private final TruckRepository truckRepository;
    private final GarageRepository garageRepository;

    public void addTruck(TruckDTO truckDTO) {

        Garage garage = garageRepository.findById(truckDTO.getIdGarage()).orElseThrow(() ->
                new GarageNotFoundException(GarageError.GARAGE_NOT_FOUND, HttpStatus.NOT_FOUND));

        if(truckDTO.getIdTruck() != null) {
            throw new CannotAssignKeyToAddingRecordException(GeneralError.CANNOT_ASSIGN_KEY_TO_ADDING_RECORD_EXCEPTION,
                    HttpStatus.CONFLICT);
        } else {
            Truck truck = Truck.of(truckDTO);
            truck.setGarage(garage);
            truckRepository.save(truck);
        }
    }

    private Truck getTruckById(Long idTruck) {
        return truckRepository.findById(idTruck).orElseThrow(() ->
                new TruckNotFoundException(TruckError.TRUCK_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<TruckDTO> getTruck(Long idTruck) {
        Truck truck = getTruckById(idTruck);
        return ResponseEntity.ok(TruckDTO.of(truck));
    }

    public List<TruckDTO> getAllTrucks() {
        return truckRepository.findAll().stream().map(truck -> TruckDTO.of(truck)).toList();
    }

    @Transactional
    public void deleteTruck(Long idTruck) {
        Truck truck = getTruckById(idTruck);
        truck.getDeliveries().forEach(delivery -> {
            if(truck.equals(delivery.getTruck())) {
                delivery.setTruck(null);
            }
        });

        truckRepository.delete(truck);
    }

    public void patchTruck(Long idTruck, TruckDTO truckDTO) {

        Truck truck = getTruckById(idTruck);

        if(Objects.nonNull(truckDTO.getIdGarage())) {
            Garage garage = garageRepository.findById(truckDTO.getIdGarage()).orElseThrow(() ->
                    new GarageNotFoundException(GarageError.GARAGE_NOT_FOUND, HttpStatus.NOT_FOUND));
            truck.setGarage(garage);
        }
        if(Objects.nonNull(truckDTO.getCapacity())) {
            truck.setCapacity(truckDTO.getCapacity());
        }

        truckRepository.save(truck);

    }
}
