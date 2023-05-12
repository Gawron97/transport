package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.dao.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TruckController {

    private final TruckRepository truckRepository;

}
