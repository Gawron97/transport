package com.rekrutacja.transport.dao;

import com.rekrutacja.transport.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck, Long> {
}
