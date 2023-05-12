package com.rekrutacja.transport.service;

import com.rekrutacja.transport.dao.GarageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GarageService {

    private final GarageRepository garageRepository;

}
