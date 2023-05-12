package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.DTO.TruckDTO;
import com.rekrutacja.transport.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/trucks")
public class TruckController {

    //TODO ogolny todo -> zrobic walidacje tych encji przy dodawaniu np

    private final TruckService truckService;

    @PostMapping()
    public void addTruck(@RequestBody TruckDTO truckDTO) {
        truckService.addOrUpdateTruck(truckDTO);
    }

    @GetMapping("/{idTruck}")
    public ResponseEntity<TruckDTO> getTruck(@PathVariable Long idTruck) {
        return truckService.getTruckById(idTruck);
    }

    @DeleteMapping("/{idTruck}")
    public void deleteTruck(@PathVariable Long idTruck) {
        truckService.deleteTruck(idTruck);
    }

}
