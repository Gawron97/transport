package com.rekrutacja.transport.service;


import com.rekrutacja.transport.dao.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TruckService {

    private final TruckRepository truckRepository;

}
