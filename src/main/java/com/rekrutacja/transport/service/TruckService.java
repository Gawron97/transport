package com.rekrutacja.transport.service;


import com.rekrutacja.transport.DTO.TruckDTO;
import com.rekrutacja.transport.dao.TruckRepository;
import com.rekrutacja.transport.model.Delivery;
import com.rekrutacja.transport.model.Truck;
import com.rekrutacja.transport.utils.trucks.exceptions.TruckNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TruckService {

    private final TruckRepository truckRepository;

    public void addOrUpdateTruck(TruckDTO truckDTO) {
        if(truckDTO.getIdTruck() != null) {
            updateTruck(truckDTO);
        } else {
            addTruck(truckDTO);
        }
    }


    private void addTruck(TruckDTO truckDTO) {
        Truck truck = Truck.of(truckDTO);
        truckRepository.save(truck);
    }

    private void updateTruck(TruckDTO truckDTO) {

        Truck truck = getTruck(truckDTO.getIdTruck());
        truckRepository.save(truck);

    }

    private Truck getTruck(Long idTruck) {
        return truckRepository.findById(idTruck).orElseThrow(() -> {
            throw new TruckNotFoundException();
        });
    }

    public ResponseEntity<TruckDTO> getTruckById(Long idTruck) {
        Truck truck = getTruck(idTruck);
        return ResponseEntity.ok(TruckDTO.of(truck));
    }

    @Transactional
    public void deleteTruck(Long idTruck) {
        Truck truck = getTruck(idTruck);
        truck.removeDeliveries();

        truckRepository.delete(truck);
    }
}
