package com.rekrutacja.transport.dao;

import com.rekrutacja.transport.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarageRepository extends JpaRepository<Garage, Long> {
}
