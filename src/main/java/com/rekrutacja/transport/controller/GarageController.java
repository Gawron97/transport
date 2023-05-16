package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.DTO.GarageDTO;
import com.rekrutacja.transport.DTO.GarageWithAssociationsDTO;
import com.rekrutacja.transport.service.GarageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/garages")
public class GarageController {

    private final GarageService garageService;

    @PostMapping
    public void addGarage(@RequestBody GarageDTO garageDTO) {
        garageService.addGarage(garageDTO);
    }

    @GetMapping("/{idGarage}")
    public ResponseEntity<GarageDTO> getGarage(@PathVariable Long idGarage) {
        return garageService.getGarage(idGarage);
    }

    @GetMapping
    public List<GarageDTO> getAllGarages() {
        return garageService.getAllGarages();
    }

    @GetMapping("/with_details")
    public List<GarageWithAssociationsDTO> getAllGarageWithAssociations() {
        return garageService.getAllGaragesWithAssociations();
    }

    @GetMapping("with_details/{idGarage}")
    public ResponseEntity<GarageWithAssociationsDTO> getGarageWithAssociations(@PathVariable Long idGarage) {
        return garageService.getGarageWithAssociations(idGarage);
    }

    @DeleteMapping("/{idGarage}")
    public void deleteGarage(@PathVariable Long idGarage) {
        garageService.deleteGarage(idGarage);
    }


}
