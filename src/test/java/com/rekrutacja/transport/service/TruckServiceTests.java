package com.rekrutacja.transport.service;

import com.rekrutacja.transport.DTO.TruckDTO;
import com.rekrutacja.transport.dao.GarageRepository;
import com.rekrutacja.transport.dao.TruckRepository;
import com.rekrutacja.transport.model.Garage;
import com.rekrutacja.transport.model.Truck;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class TruckServiceTests {

    private TruckService truckService;
    private static TruckRepository truckRepository;
    private static GarageRepository garageRepository;

    @BeforeAll
    public static void init() {
        truckRepository = Mockito.mock(TruckRepository.class);
        garageRepository = Mockito.mock(GarageRepository.class);
    }

    @BeforeEach
    public void prepare() {
        truckService = new TruckService(truckRepository, garageRepository);
    }

    @Test
    public void givenGarageWithoutTrucksWhenAddTruckThenTruckSavedInGarage() {

        //Given
        Garage garage = new Garage();
        garage.setIdGarage(1L);
        garage.setName("Garaz w Opolu");

        //When
        TruckDTO truckDTO = TruckDTO.builder()
                .brand("Scania")
                .model("v5")
                .idGarage(1L)
                .build();

        Mockito.when(garageRepository.findById(1L)).thenReturn(Optional.of(garage));
        truckService.addTruck(truckDTO);


        //Then
        Truck expectedTruck = new Truck();
        expectedTruck.setBrand("Scania");
        expectedTruck.setModel("v5");
        expectedTruck.setGarage(garage);

        Mockito.verify(truckRepository).save(expectedTruck);

    }



}