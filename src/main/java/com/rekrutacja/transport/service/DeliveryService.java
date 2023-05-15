package com.rekrutacja.transport.service;

import com.rekrutacja.transport.DTO.DeliveryDTO;
import com.rekrutacja.transport.DTO.DriverDTO;
import com.rekrutacja.transport.DTO.TruckDTO;
import com.rekrutacja.transport.dao.DeliveryRepository;
import com.rekrutacja.transport.dao.DriverRepository;
import com.rekrutacja.transport.dao.TruckRepository;
import com.rekrutacja.transport.model.Delivery;
import com.rekrutacja.transport.model.Driver;
import com.rekrutacja.transport.model.Truck;
import com.rekrutacja.transport.model.enums.Status;
import com.rekrutacja.transport.utils.delivery.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        validateDriverAndTruck(driver, truck);

        if(deliveryDTO.getIdDelivery() != null) {
            updateDelivery(deliveryDTO, truck, driver);
        } else {
            addDelivery(deliveryDTO, truck, driver);
        }

    }

    private void validateDriverAndTruck(Driver driver, Truck truck) {
        if(Status.NOT_AVAILABLE.equals(driver.getStatus())) {
            throw new CannotAssignNotAvailableDriverException(DeliveryError.CANNOT_ASSIGN_NOT_AVAILABLE_DRIVER,
                    HttpStatus.BAD_REQUEST);
        }
        if(Status.NOT_AVAILABLE.equals(truck.getStatus())) {
            throw new CannotAssignNotAvailableTruckException(DeliveryError.CANNOT_ASSIGN_NOT_AVAILABLE_TRUCK,
                    HttpStatus.BAD_REQUEST);
        }
    }

    private void updateDelivery(DeliveryDTO deliveryDTO, Truck truck, Driver driver) {

        Delivery delivery = getDeliveryById(deliveryDTO.getIdDelivery());
        delivery.setDriver(driver);
        delivery.setTruck(truck);
        deliveryRepository.save(delivery);

    }

    private void addDelivery(DeliveryDTO deliveryDTO, Truck truck, Driver driver) {

        Delivery delivery = Delivery.of(deliveryDTO);
        delivery.setDriver(driver);
        delivery.setTruck(truck);
        deliveryRepository.save(delivery);

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
}
