package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.dao.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverController {

    private final DriverRepository driverRepository;

}
