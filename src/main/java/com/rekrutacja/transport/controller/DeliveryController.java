package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.DTO.DeliveryDTO;
import com.rekrutacja.transport.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public void addOrUpdateDelivery(@RequestBody @Valid DeliveryDTO deliveryDTO) {
        deliveryService.addOrUpdateDelivery(deliveryDTO);
    }

    @GetMapping("/{idDelivery}")
    public ResponseEntity<DeliveryDTO> getDelivery(@PathVariable Long idDelivery) {
        return deliveryService.getDelivery(idDelivery);
    }

    @PatchMapping("/{idDelivery}")
    public void patchDelivery(@PathVariable Long idDelivery, @RequestBody DeliveryDTO deliveryDTO) {
        deliveryService.patchDelivery(idDelivery, deliveryDTO);
    }

    @DeleteMapping(value = "/{idDelivery}")
    public void deleteDelivery(@PathVariable Long idDelivery) {
        deliveryService.deleteDelivery(idDelivery);
    }

}
