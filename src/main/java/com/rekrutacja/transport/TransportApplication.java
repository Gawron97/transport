package com.rekrutacja.transport;

import com.rekrutacja.transport.dao.DeliveryRepository;
import com.rekrutacja.transport.dao.GarageRepository;
import com.rekrutacja.transport.dao.TruckRepository;
import com.rekrutacja.transport.model.Delivery;
import com.rekrutacja.transport.model.enums.DeliveryStatus;
import com.rekrutacja.transport.model.Garage;
import com.rekrutacja.transport.model.Truck;
import com.rekrutacja.transport.service.TruckService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransportApplication implements CommandLineRunner {

	@Autowired
	DeliveryRepository deliveryRepository;
	@Autowired
	GarageRepository garageRepository;
	@Autowired
	TruckRepository truckRepository;
	@Autowired
	TruckService truckService;


	public static void main(String[] args) {
		SpringApplication.run(TransportApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

//		Garage garage = new Garage();
//		garage.setName("asd");
//		garageRepository.save(garage);
//
//		Truck truck = new Truck();
//		truck.setModel("cos");
//		truck.setGarage(garage);
//		truckRepository.save(truck);

//		truckService.deleteTruck(1L);

//		Truck truck2 = new Truck();
//		truck2.setModel("ssss");
//		truckRepository.save(truck2);
//
//		Delivery delivery = new Delivery();
//		delivery.setStatus(DeliveryStatus.ON_THE_WAY);
//		delivery.setItemName("cos");
//		Truck truck = truckRepository.findById(1L).get();
//		delivery.setTruck(truck);
//		deliveryRepository.save(delivery);
//		System.out.println(delivery.getTruck());

//		truckService.deleteTruck(1L);

//		Garage garage = new Garage();
//		garage.setName("test");
//
//		Truck truck = new Truck();
//		truck.setGarage(garage);
//		garage.getTrucks().add(truck);
//
//		garageRepository.save(garage);
//		truckRepository.save(truck);
//
//		Delivery delivery = new Delivery();
//		delivery.setTruck(truck);
//		delivery.setItemName("cos");
//		delivery.setStatus(DeliveryStatus.ON_THE_WAY);
//
//		deliveryRepository.save(delivery);
//
//		deliveryRepository.save(delivery);
//
//		Truck truck1 = truckRepository.getById(1L);
//
//		truckRepository.save(truck1);
//
//		System.out.println(truckRepository.findById(1L).get().getDeliveries());
	}
}
