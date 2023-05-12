package com.rekrutacja.transport.dao;

import com.rekrutacja.transport.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
