package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.DTO.DriverDTO;
import com.rekrutacja.transport.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/drivers")
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public void addDriver(@RequestBody @Valid DriverDTO driverDTO) {
        driverService.addDriver(driverDTO);
    }

    @GetMapping("/{idDriver}")
    public ResponseEntity<DriverDTO> getDriver(@PathVariable Long idDriver) {
        return driverService.getDriver(idDriver);
    }

    @GetMapping
    public List<DriverDTO> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @PatchMapping("/{idDriver}")
    public void patchDriver(@PathVariable Long idDriver, @RequestBody @Valid DriverDTO driverDTO) {
        driverService.patchDriver(idDriver, driverDTO);
    }

    @DeleteMapping(value = "/{idDriver}")
    public void deleteDriver(@PathVariable Long idDriver) {
        driverService.deleteDriver(idDriver);
    }


}
