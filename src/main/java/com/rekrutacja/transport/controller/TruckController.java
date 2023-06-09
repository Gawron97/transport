package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.DTO.TruckDTO;
import com.rekrutacja.transport.service.TruckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/trucks")
public class TruckController {

    private final TruckService truckService;

    @PostMapping
    public void addTruck(@RequestBody @Valid TruckDTO truckDTO) {
        truckService.addTruck(truckDTO);
    }

    @GetMapping("/{idTruck}")
    public ResponseEntity<TruckDTO> getTruck(@PathVariable Long idTruck) {
        return truckService.getTruck(idTruck);
    }

    @GetMapping
    public List<TruckDTO> getAllTrucks() {
        return truckService.getAllTrucks();
    }

    @PatchMapping("/{idTruck}")
    public void patchTruck(@PathVariable Long idTruck, @RequestBody @Valid TruckDTO truckDTO) {
        truckService.patchTruck(idTruck, truckDTO);
    }

    @DeleteMapping("/{idTruck}")
    public void deleteTruck(@PathVariable Long idTruck) {
        truckService.deleteTruck(idTruck);
    }

}
