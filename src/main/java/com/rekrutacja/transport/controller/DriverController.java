package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.dao.DriverRepository;
import com.rekrutacja.transport.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverController {

    private final DeliveryService deliveryService;

}
