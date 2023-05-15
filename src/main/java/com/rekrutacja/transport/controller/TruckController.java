package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.DTO.TruckDTO;
import com.rekrutacja.transport.service.TruckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/trucks")
public class TruckController {

    //TODO ogolny todo -> zrobic walidacje tych encji przy dodawaniu np

    private final TruckService truckService;

    @PostMapping
    public void addOrUpdateTruck(@RequestBody @Valid TruckDTO truckDTO) {
        truckService.addOrUpdateTruck(truckDTO);
    }

    @GetMapping("/{idTruck}")
    public ResponseEntity<TruckDTO> getTruck(@PathVariable Long idTruck) {
        return truckService.getTruck(idTruck);
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
