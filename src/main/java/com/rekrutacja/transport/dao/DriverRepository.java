package com.rekrutacja.transport.dao;

import com.rekrutacja.transport.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
