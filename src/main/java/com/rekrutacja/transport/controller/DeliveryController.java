package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.DTO.DeliveryDTO;
import com.rekrutacja.transport.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public void addOrUpdateDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        deliveryService.addOrUpdateDelivery(deliveryDTO);
    }

    @GetMapping("/{idDelivery}")
    public ResponseEntity<DeliveryDTO> getDelivery(@PathVariable Long idDelivery) {
        return deliveryService.getDelivery(idDelivery);
    }

    @DeleteMapping(value = "/{idDelivery}")
    public void deleteDelivery(@PathVariable Long idDelivery) {
        deliveryService.deleteDelivery(idDelivery);
    }

}
