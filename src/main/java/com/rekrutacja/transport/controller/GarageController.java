package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.service.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GarageController {

    private final GarageService garageService;

}
