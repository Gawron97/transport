package com.rekrutacja.transport.controller;

import com.rekrutacja.transport.DTO.DeliveryDTO;
import com.rekrutacja.transport.model.enums.DeliveryStatus;
import com.rekrutacja.transport.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public void addDelivery(@RequestBody @Valid DeliveryDTO deliveryDTO) {
        deliveryService.addDelivery(deliveryDTO);
    }

    @GetMapping("/{idDelivery}")
    public ResponseEntity<DeliveryDTO> getDelivery(@PathVariable Long idDelivery) {
        return deliveryService.getDelivery(idDelivery);
    }

    @GetMapping
    public List<DeliveryDTO> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @PostMapping("/change_status/{idDelivery}")
    public void changeDeliveryStatus(@PathVariable Long idDelivery, @RequestParam DeliveryStatus deliveryStatus) {
        deliveryService.changeDeliveryStatus(idDelivery, deliveryStatus);
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
