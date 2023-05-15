package com.rekrutacja.transport.service;


import com.rekrutacja.transport.DTO.TruckDTO;
import com.rekrutacja.transport.dao.GarageRepository;
import com.rekrutacja.transport.dao.TruckRepository;
import com.rekrutacja.transport.model.Garage;
import com.rekrutacja.transport.model.Truck;
import com.rekrutacja.transport.utils.garage.exceptions.GarageError;
import com.rekrutacja.transport.utils.garage.exceptions.GarageNotFoundException;
import com.rekrutacja.transport.utils.trucks.exceptions.TruckError;
import com.rekrutacja.transport.utils.trucks.exceptions.TruckNeedGarageException;
import com.rekrutacja.transport.utils.trucks.exceptions.TruckNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TruckService {

    private final TruckRepository truckRepository;
    private final GarageRepository garageRepository;

    public void addOrUpdateTruck(TruckDTO truckDTO) {

        Garage garage = garageRepository.findById(truckDTO.getIdGarage()).orElseThrow(() ->
                new GarageNotFoundException(GarageError.GARAGE_NOT_FOUND, HttpStatus.NOT_FOUND));

        if(truckDTO.getIdTruck() != null) {
            updateTruck(truckDTO, garage);
        } else {
            addTruck(truckDTO, garage);
        }
    }

    private void addTruck(TruckDTO truckDTO, Garage garage) {
        Truck truck = Truck.of(truckDTO);
        truck.setGarage(garage);
        truckRepository.save(truck);
    }

    private void updateTruck(TruckDTO truckDTO, Garage garage) {

        Truck truck = getTruckById(truckDTO.getIdTruck());
        truck.setGarage(garage);
        truckRepository.save(truck);

    }

    private Truck getTruckById(Long idTruck) {
        return truckRepository.findById(idTruck).orElseThrow(() ->
                new TruckNotFoundException(TruckError.TRUCK_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<TruckDTO> getTruck(Long idTruck) {
        Truck truck = getTruckById(idTruck);
        return ResponseEntity.ok(TruckDTO.of(truck));
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

        if(Objects.nonNull(truckDTO.getStatus())) {
            truck.setStatus(truckDTO.getStatus());
        }
        if(Objects.nonNull(truckDTO.getIdGarage())) {
            Garage garage = garageRepository.findById(truckDTO.getIdGarage()).orElseThrow(() ->
                    new GarageNotFoundException(GarageError.GARAGE_NOT_FOUND, HttpStatus.NOT_FOUND));
            truck.setGarage(garage);
        }

        truckRepository.save(truck);

    }
}
