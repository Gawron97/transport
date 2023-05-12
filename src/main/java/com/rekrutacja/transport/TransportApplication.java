package com.rekrutacja.transport;

import com.rekrutacja.transport.dao.DeliveryRepository;
import com.rekrutacja.transport.dao.GarageRepository;
import com.rekrutacja.transport.dao.TruckRepository;
import com.rekrutacja.transport.model.Delivery;
import com.rekrutacja.transport.model.DeliveryStatus;
import com.rekrutacja.transport.model.Garage;
import com.rekrutacja.transport.model.Truck;
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


	public static void main(String[] args) {
		SpringApplication.run(TransportApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Garage garage = new Garage();
		garage.setName("test");

		Truck truck = new Truck();
		truck.setGarage(garage);
		garage.getTrucks().add(truck);

		garageRepository.save(garage);
		truckRepository.save(truck);

		Delivery delivery = new Delivery();
		delivery.setTruck(truck);
		delivery.setItemName("cos");
		delivery.setStatus(DeliveryStatus.ON_THE_WAY);

		deliveryRepository.save(delivery);

		deliveryRepository.save(delivery);

		Truck truck1 = truckRepository.getById(1L);
		truck1.getDeliveries().add(delivery);

		truckRepository.save(truck1);

		System.out.println(truckRepository.getById(1L).getDeliveries());
	}
}
