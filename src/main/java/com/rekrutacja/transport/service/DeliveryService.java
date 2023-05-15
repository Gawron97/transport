package com.rekrutacja.transport.service;

import com.rekrutacja.transport.DTO.DeliveryDTO;
import com.rekrutacja.transport.dao.DeliveryRepository;
import com.rekrutacja.transport.dao.DriverRepository;
import com.rekrutacja.transport.dao.TruckRepository;
import com.rekrutacja.transport.model.Delivery;
import com.rekrutacja.transport.model.Driver;
import com.rekrutacja.transport.model.Truck;
import com.rekrutacja.transport.model.enums.DeliveryStatus;
import com.rekrutacja.transport.model.enums.Status;
import com.rekrutacja.transport.utils.delivery.exceptions.*;
import com.rekrutacja.transport.utils.driver.exceptions.DriverError;
import com.rekrutacja.transport.utils.driver.exceptions.DriverNotFoundException;
import com.rekrutacja.transport.utils.trucks.exceptions.TruckError;
import com.rekrutacja.transport.utils.trucks.exceptions.TruckNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final TruckRepository truckRepository;
    private final DriverRepository driverRepository;

    public void addOrUpdateDelivery(DeliveryDTO deliveryDTO) {

        Truck truck = truckRepository.findById(deliveryDTO.getIdTruck()).orElseThrow(() ->
                new DeliveryNeedTruckException(DeliveryError.DELIVERY_NEED_TRUCK, HttpStatus.BAD_REQUEST));
        Driver driver = driverRepository.findById(deliveryDTO.getIdDriver()).orElseThrow(() ->
                new DeliveryNeedDriverException(DeliveryError.DELIVERY_NEED_DRIVER, HttpStatus.BAD_REQUEST));

        validate(deliveryDTO, driver, truck);

        Delivery delivery;

        if(deliveryDTO.getIdDelivery() != null) {
            delivery = getDeliveryById(deliveryDTO.getIdDelivery());
        } else {
            delivery = Delivery.of(deliveryDTO);
            delivery.setStatus(DeliveryStatus.ON_THE_WAY);
        }

        delivery.setDriver(driver);
        delivery.setTruck(truck);
        driver.setStatus(Status.NOT_AVAILABLE);
        truck.setStatus(Status.NOT_AVAILABLE);
        deliveryRepository.save(delivery);

    }

    private void validate(DeliveryDTO deliveryDTO, Driver driver, Truck truck) {
        if(Status.NOT_AVAILABLE.equals(driver.getStatus())) {
            throw new CannotAssignNotAvailableDriverException(DeliveryError.CANNOT_ASSIGN_NOT_AVAILABLE_DRIVER,
                    HttpStatus.BAD_REQUEST);
        }
        if(Status.NOT_AVAILABLE.equals(truck.getStatus())) {
            throw new CannotAssignNotAvailableTruckException(DeliveryError.CANNOT_ASSIGN_NOT_AVAILABLE_TRUCK,
                    HttpStatus.BAD_REQUEST);
        }
        if(truck.getCapacity() < deliveryDTO.getWeight()) {
            throw new NotEnoughCapacityInTruckException(DeliveryError.NOT_ENOUGH_CAPACITY_IN_TRUCK,HttpStatus.BAD_REQUEST);
        }
    }

    private Delivery getDeliveryById(Long idDelivery) {
        return deliveryRepository.findById(idDelivery).orElseThrow(() ->
                new DeliveryNotFoundException(DeliveryError.DELIVERY_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<DeliveryDTO> getDelivery(Long idDelivery) {
        Delivery delivery = getDeliveryById(idDelivery);
        return ResponseEntity.ok(DeliveryDTO.of(delivery));
    }

    public void deleteDelivery(Long idDelivery) {

        Delivery delivery = getDeliveryById(idDelivery);
        deliveryRepository.delete(delivery);

    }

    public void patchDelivery(Long idDelivery, DeliveryDTO deliveryDTO) {

        Delivery delivery = getDeliveryById(idDelivery);

        if(Objects.nonNull(deliveryDTO.getDeliveryStatus())) {
            delivery.setStatus(deliveryDTO.getDeliveryStatus());
            if(DeliveryStatus.DELIVERED.equals(deliveryDTO.getDeliveryStatus())) {
                Truck truck = truckRepository.findById(deliveryDTO.getIdTruck()).orElseThrow(() ->
                        new TruckNotFoundException(TruckError.TRUCK_NOT_FOUND, HttpStatus.NOT_FOUND));
                Driver driver = driverRepository.findById(deliveryDTO.getIdDriver()).orElseThrow(() ->
                        new DriverNotFoundException(DriverError.DRIVER_NOT_FOUND, HttpStatus.NOT_FOUND));
                truck.setStatus(Status.AVAILABLE);
                driver.setStatus(Status.AVAILABLE);
            }
        }

        deliveryRepository.save(delivery);

    }
}
