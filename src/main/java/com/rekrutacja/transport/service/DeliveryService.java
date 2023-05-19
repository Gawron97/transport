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
import com.rekrutacja.transport.utils.generalExceptions.GeneralError;
import com.rekrutacja.transport.utils.generalExceptions.CannotAssignKeyToAddingRecordException;
import com.rekrutacja.transport.utils.trucks.exceptions.TruckError;
import com.rekrutacja.transport.utils.trucks.exceptions.TruckNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final TruckRepository truckRepository;
    private final DriverRepository driverRepository;

    public void addDelivery(DeliveryDTO deliveryDTO) {

        if(deliveryDTO.getIdDelivery() != null) {
            throw new CannotAssignKeyToAddingRecordException(GeneralError.CANNOT_ASSIGN_KEY_TO_ADDING_RECORD_EXCEPTION,
                    HttpStatus.BAD_REQUEST);
        }

        Truck truck = truckRepository.findById(deliveryDTO.getIdTruck()).orElseThrow(() ->
                new TruckNotFoundException(TruckError.TRUCK_NOT_FOUND, HttpStatus.NOT_FOUND));
        Driver driver = driverRepository.findById(deliveryDTO.getIdDriver()).orElseThrow(() ->
                new DriverNotFoundException(DriverError.DRIVER_NOT_FOUND, HttpStatus.NOT_FOUND));

        validate(deliveryDTO, driver, truck);

        Delivery delivery = Delivery.of(deliveryDTO);

        delivery.setDriver(driver);
        delivery.setTruck(truck);
        if(!DeliveryStatus.DELIVERED.equals(delivery.getStatus())) {
            setTruckAndDriverStatus(delivery);
        }
        deliveryRepository.save(delivery);

    }

    private void validate(DeliveryDTO deliveryDTO, Driver driver, Truck truck) {
        if(!Objects.equals(driver.getGarage(), truck.getGarage())) {
            throw new DriverAndTruckNotInSameGaragesException(DeliveryError.DRIVER_AND_TRUCK_HAVE_TO_BE_IN_THE_SAME_GARAGE,
                    HttpStatus.BAD_REQUEST);
        }
        if(!DeliveryStatus.DELIVERED.equals(deliveryDTO.getDeliveryStatus()) &&
                Status.NOT_AVAILABLE.equals(driver.getStatus())) {
            throw new CannotAssignNotAvailableDriverException(DeliveryError.CANNOT_ASSIGN_NOT_AVAILABLE_DRIVER,
                    HttpStatus.BAD_REQUEST);
        }
        if(!DeliveryStatus.DELIVERED.equals(deliveryDTO.getDeliveryStatus()) &&
                Status.NOT_AVAILABLE.equals(truck.getStatus())) {
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

    public List<DeliveryDTO> getAllDeliveries() {
        return deliveryRepository.findAll().stream().map(delivery -> DeliveryDTO.of(delivery)).toList();
    }

    public void deleteDelivery(Long idDelivery) {

        Delivery delivery = getDeliveryById(idDelivery);
        deliveryRepository.delete(delivery);

    }

    private void setTruckAndDriverStatus(Delivery delivery) {

        if(DeliveryStatus.DELIVERED.equals(delivery.getStatus())) {
            delivery.getTruck().setStatus(Status.AVAILABLE);
            delivery.getDriver().setStatus(Status.AVAILABLE);
        } else {
            delivery.getTruck().setStatus(Status.NOT_AVAILABLE);
            delivery.getDriver().setStatus(Status.NOT_AVAILABLE);
        }

    }

    public void changeDeliveryStatus(Long idDelivery, DeliveryStatus deliveryStatus) {
        Delivery delivery = getDeliveryById(idDelivery);
        delivery.setStatus(deliveryStatus);
        setTruckAndDriverStatus(delivery);

        deliveryRepository.save(delivery);
    }

    public void patchDelivery(Long idDelivery, DeliveryDTO deliveryDTO) {

        Delivery delivery = getDeliveryById(idDelivery);

        if(Objects.nonNull(deliveryDTO.getDeliveryStatus())) {
            delivery.setStatus(deliveryDTO.getDeliveryStatus());
            setTruckAndDriverStatus(delivery);
        }

        deliveryRepository.save(delivery);

    }
}
