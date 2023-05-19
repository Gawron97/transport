package com.rekrutacja.transport.service;

import com.rekrutacja.transport.DTO.DeliveryDTO;
import com.rekrutacja.transport.dao.DeliveryRepository;
import com.rekrutacja.transport.dao.DriverRepository;
import com.rekrutacja.transport.dao.TruckRepository;
import com.rekrutacja.transport.model.Delivery;
import com.rekrutacja.transport.model.Driver;
import com.rekrutacja.transport.model.Garage;
import com.rekrutacja.transport.model.Truck;
import com.rekrutacja.transport.model.enums.DeliveryStatus;
import com.rekrutacja.transport.model.enums.Status;
import com.rekrutacja.transport.utils.delivery.exceptions.CannotAssignNotAvailableDriverException;
import com.rekrutacja.transport.utils.delivery.exceptions.CannotAssignNotAvailableTruckException;
import com.rekrutacja.transport.utils.delivery.exceptions.DriverAndTruckNotInSameGaragesException;
import com.rekrutacja.transport.utils.delivery.exceptions.NotEnoughCapacityInTruckException;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.nullness.Opt;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceTests {

    @InjectMocks
    private DeliveryService deliveryService;
    @Mock
    private static DeliveryRepository deliveryRepository;
    @Mock
    private static TruckRepository truckRepository;
    @Mock
    private static DriverRepository driverRepository;


    private Truck createBasicTruck() {
        Truck truck = new Truck();
        truck.setIdTruck(1L);
        truck.setCapacity(100.0);
        truck.setBrand("Scania");
        truck.setModel("v5");
        truck.setStatus(Status.AVAILABLE);

        return truck;
    }

    private Driver createBasicDriver() {
        Driver driver = new Driver();
        driver.setIdDriver(1L);
        driver.setSurname("Ks");
        driver.setName("Jan");
        driver.setStatus(Status.AVAILABLE);

        return driver;
    }

    private Garage createBasicGarage() {
        Garage garage = new Garage();
        garage.setIdGarage(1L);
        garage.setName("garaz");
        return garage;
    }


    @Test
    public void givenAvailableTruckAndDriverInOneGarageWhenAddThemToNewDeliveryThenDeliverySaved() {

        //Given
        Garage garage = createBasicGarage();

        Truck truck = createBasicTruck();
        truck.setGarage(garage);
        garage.getTrucks().add(truck);

        Driver driver = createBasicDriver();
        driver.setGarage(garage);
        garage.getDrivers().add(driver);

        //When
        DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                .itemName("pszenica")
                .weight(10.0)
                .deliveryStatus(DeliveryStatus.ON_THE_WAY)
                .idTruck(1L)
                .idDriver(1L)
                .build();

        when(truckRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        deliveryService.addDelivery(deliveryDTO);

        //Then
        Delivery expectedDelivery = new Delivery();
        expectedDelivery.setItemName("pszenica");
        expectedDelivery.setWeight(10.0);
        expectedDelivery.setStatus(DeliveryStatus.ON_THE_WAY);
        expectedDelivery.setDriver(driver);
        expectedDelivery.setTruck(truck);

        verify(deliveryRepository).save(expectedDelivery);

    }

    @Test
    public void givenUnAvailableTruckWhenSetItToDeliveryThenExceptionThrew() {

        //Given

        Garage garage = createBasicGarage();

        Truck unavailableTruck = createBasicTruck();
        unavailableTruck.setStatus(Status.NOT_AVAILABLE);
        unavailableTruck.setGarage(garage);

        Driver driver = createBasicDriver();
        driver.setGarage(garage);

        //When
        DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                .itemName("cars")
                .weight(10.0)
                .deliveryStatus(DeliveryStatus.ON_THE_WAY)
                .idTruck(1L)
                .idDriver(1L)
                .build();

        when(truckRepository.findById(1L)).thenReturn(Optional.of(unavailableTruck));
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        //Then

        assertThrows(CannotAssignNotAvailableTruckException.class, () -> deliveryService.addDelivery(deliveryDTO));
        verify(deliveryRepository, times(0)).save(any());

    }

    @Test
    public void givenUnAvailableDriverWhenSetItToDeliveryThenExceptionThrew() {

        //Given

        Garage garage = createBasicGarage();

        Truck truck = createBasicTruck();
        truck.setGarage(garage);

        Driver unavailableDriver = createBasicDriver();
        unavailableDriver.setStatus(Status.NOT_AVAILABLE);
        unavailableDriver.setGarage(garage);

        //When
        DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                .itemName("cars")
                .weight(10.0)
                .deliveryStatus(DeliveryStatus.ON_THE_WAY)
                .idTruck(1L)
                .idDriver(1L)
                .build();

        when(truckRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(driverRepository.findById(1L)).thenReturn(Optional.of(unavailableDriver));

        //Then

        assertThrows(CannotAssignNotAvailableDriverException.class, () -> deliveryService.addDelivery(deliveryDTO));
        verify(deliveryRepository, times(0)).save(any());

    }

    @Test
    public void givenTruckWithTooSmallCapacityWhenSetItToDeliveryThenExceptionThrew() {

        //Given

        Garage garage = createBasicGarage();

        Truck truck = createBasicTruck();
        truck.setCapacity(10.0);
        truck.setGarage(garage);

        Driver driver = createBasicDriver();
        driver.setGarage(garage);

        //When
        DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                .itemName("cars")
                .weight(20.0)
                .deliveryStatus(DeliveryStatus.ON_THE_WAY)
                .idTruck(1L)
                .idDriver(1L)
                .build();

        when(truckRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        //Then

        assertThrows(NotEnoughCapacityInTruckException.class, () -> deliveryService.addDelivery(deliveryDTO));
        verify(deliveryRepository, times(0)).save(any());

    }

    @Test
    public void givenAvailableDriverAndTruckWhenAssignToDeliveryOnTheWayThenTheyUnavailable() {

        //Given

        Garage garage = createBasicGarage();

        Truck truck = createBasicTruck();
        truck.setGarage(garage);

        Driver driver = createBasicDriver();
        driver.setGarage(garage);

        //When
        DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                .itemName("cars")
                .weight(10.0)
                .deliveryStatus(DeliveryStatus.ON_THE_WAY)
                .idTruck(1L)
                .idDriver(1L)
                .build();

        when(truckRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        deliveryService.addDelivery(deliveryDTO);

        //Then
        assertEquals(Status.NOT_AVAILABLE, truck.getStatus());
        assertEquals(Status.NOT_AVAILABLE, driver.getStatus());

    }

    @Test
    public void givenTruckAndDriverFromDifferentGaragesWhenAssignToDeliveryThenExceptionThrew() {

        //Given

        Garage garage = createBasicGarage();
        Garage garage2 = new Garage();
        garage2.setIdGarage(2L);
        garage2.setName("different");

        Truck truck = createBasicTruck();
        truck.setGarage(garage);

        Driver driver = createBasicDriver();
        driver.setGarage(garage2);

        //When

        DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                .itemName("cars")
                .weight(10.0)
                .deliveryStatus(DeliveryStatus.ON_THE_WAY)
                .idTruck(1L)
                .idDriver(1L)
                .build();

        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));
        when(truckRepository.findById(1L)).thenReturn(Optional.of(truck));

        //Then

        assertThrows(DriverAndTruckNotInSameGaragesException.class, () -> deliveryService.addDelivery(deliveryDTO));
    }

    @Test
    public void givenDeliveriesWhenGetThemThenGetTheseDeliveries() {

        //Given

        Delivery delivery = new Delivery();
        delivery.setItemName("item1");
        delivery.setWeight(10.0);
        delivery.setStatus(DeliveryStatus.ON_THE_WAY);

        Delivery delivery2 = new Delivery();
        delivery.setItemName("item2");
        delivery.setWeight(10.0);
        delivery.setStatus(DeliveryStatus.ON_THE_WAY);

        Delivery delivery3 = new Delivery();
        delivery.setItemName("item3");
        delivery.setWeight(10.0);
        delivery.setStatus(DeliveryStatus.DELIVERED);

        Delivery delivery4 = new Delivery();
        delivery.setItemName("item4");
        delivery.setWeight(10.0);
        delivery.setStatus(DeliveryStatus.ON_THE_WAY);

        List<Delivery> deliveryList = List.of(delivery, delivery2, delivery3, delivery4);

        //When

        when(deliveryRepository.findAll()).thenReturn(deliveryList);

        //Then

        assertEquals(deliveryService.getAllDeliveries().size(), deliveryList.size());
        assertEquals(deliveryService.getAllDeliveries(), deliveryList.stream().map(DeliveryDTO::of).toList());

    }

    @Test
    public void givenAvailableDriverAndTruckWhenAssignToDeliveryDeliveredThenTheyAvailable() {

        //Given

        Garage garage = createBasicGarage();

        Truck truck = createBasicTruck();
        truck.setGarage(garage);

        Driver driver = createBasicDriver();
        driver.setGarage(garage);

        //When
        DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                .itemName("cars")
                .weight(10.0)
                .deliveryStatus(DeliveryStatus.DELIVERED)
                .idTruck(1L)
                .idDriver(1L)
                .build();

        when(truckRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        deliveryService.addDelivery(deliveryDTO);

        //Then
        assertEquals(Status.AVAILABLE, truck.getStatus());
        assertEquals(Status.AVAILABLE, driver.getStatus());

    }

    @Test
    public void givenUnavailableDriverAndTruckWhenAssignToDeliveryThenTheyUnavailable() {

        //Given

        Garage garage = createBasicGarage();

        Truck truck = createBasicTruck();
        truck.setStatus(Status.NOT_AVAILABLE);
        truck.setGarage(garage);

        Driver driver = createBasicDriver();
        driver.setStatus(Status.NOT_AVAILABLE);
        driver.setGarage(garage);

        //When
        DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                .itemName("cars")
                .weight(10.0)
                .deliveryStatus(DeliveryStatus.DELIVERED)
                .idTruck(1L)
                .idDriver(1L)
                .build();

        when(truckRepository.findById(1L)).thenReturn(Optional.of(truck));
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        deliveryService.addDelivery(deliveryDTO);

        //Then
        assertEquals(Status.NOT_AVAILABLE, truck.getStatus());
        assertEquals(Status.NOT_AVAILABLE, driver.getStatus());

    }


}
