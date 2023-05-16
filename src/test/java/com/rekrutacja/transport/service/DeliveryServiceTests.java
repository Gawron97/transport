package com.rekrutacja.transport.service;

import com.rekrutacja.transport.DTO.DeliveryDTO;
import com.rekrutacja.transport.dao.DeliveryRepository;
import com.rekrutacja.transport.dao.DriverRepository;
import com.rekrutacja.transport.dao.TruckRepository;
import com.rekrutacja.transport.model.Delivery;
import com.rekrutacja.transport.model.Driver;
import com.rekrutacja.transport.model.Garage;
import com.rekrutacja.transport.model.Truck;
import com.rekrutacja.transport.model.enums.Status;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class DeliveryServiceTests {

    private DeliveryService deliveryService;
    private static DeliveryRepository deliveryRepository;
    private static TruckRepository truckRepository;
    private static DriverRepository driverRepository;

    @BeforeAll
    public static void init() {
        deliveryRepository = Mockito.mock(DeliveryRepository.class);
        truckRepository = Mockito.mock(TruckRepository.class);
        driverRepository = Mockito.mock(DriverRepository.class);
    }

    @BeforeEach
    public void prepare() {
        deliveryService = new DeliveryService(deliveryRepository, truckRepository, driverRepository);
    }

    @Test
    public void givenAvailableTruckAndDriverInOneGarageWhenAddThemToNewDeliveryThenDeliverySaved() {

        //Given
        Garage garage = new Garage();
        garage.setIdGarage(1L);
        garage.setName("garaz");

        Truck truck = new Truck();
        truck.setIdTruck(1L);
        truck.setCapacity(100.0);
        truck.setBrand("Scania");
        truck.setModel("v5");
        truck.setStatus(Status.AVAILABLE);
        truck.setGarage(garage);
        garage.getTrucks().add(truck);

        Driver driver = new Driver();
        driver.setIdDriver(1L);
        driver.setSurname("Ks");
        driver.setName("Jan");
        driver.setStatus(Status.AVAILABLE);
        driver.setGarage(garage);
        garage.getDrivers().add(driver);

        //When
        DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                .itemName("pszenica")
                .weight(10.0)
                .idTruck(1L)
                .idDriver(1L)
                .build();

        Mockito.when(truckRepository.findById(1L)).thenReturn(Optional.of(truck));
        Mockito.when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        deliveryService.addDelivery(deliveryDTO);

        //Then
        Delivery expectedDelivery = new Delivery();
        expectedDelivery.setItemName("pszenica");
        expectedDelivery.setWeight(10.0);
        expectedDelivery.setDriver(driver);
        expectedDelivery.setTruck(truck);

        Mockito.verify(deliveryRepository).save(expectedDelivery);


    }


}
