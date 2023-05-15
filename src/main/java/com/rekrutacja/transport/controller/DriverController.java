package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.DTO.DriverDTO;
import com.rekrutacja.transport.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
@RequiredArgsConstructor
@RequestMapping(value = "api/drivers")
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public void addOrUpdateDriver(@RequestBody DriverDTO driverDTO) {
        driverService.addOrUpdateDriver(driverDTO);
    }

    @GetMapping("/{idDriver}")
    public ResponseEntity<DriverDTO> getDriver(@PathVariable Long idDriver) {
        return driverService.getDriver(idDriver);
    }

    @DeleteMapping(value = "/{idDriver}")
    public void deleteDriver(@PathVariable Long idDriver) {
        driverService.deleteDriver(idDriver);
    }


}
