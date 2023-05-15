package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.DTO.GarageDTO;
import com.rekrutacja.transport.DTO.GarageWithAssociationsDTO;
import com.rekrutacja.transport.service.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/garages")
public class GarageController {

    private final GarageService garageService;

    @PostMapping
    public void addOrUpdateGarage(@RequestBody GarageDTO garageDTO) {
        garageService.addOrUpdateGarage(garageDTO);
    }

    @GetMapping("/{idGarage}")
    public ResponseEntity<GarageDTO> getGarage(@PathVariable Long idGarage) {
        return garageService.getGarage(idGarage);
    }

    @GetMapping("with_details/{idGarage}")
    public ResponseEntity<GarageWithAssociationsDTO> getGarageWithTrucks(@PathVariable Long idGarage) {
        return garageService.getGarageWithTrucks(idGarage);
    }

    @DeleteMapping("/{idGarage}")
    public void deleteGarage(@PathVariable Long idGarage) {
        garageService.deleteGarage(idGarage);
    }


}
