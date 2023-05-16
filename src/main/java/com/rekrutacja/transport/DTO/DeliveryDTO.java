package com.rekrutacja.transport.DTO;

import com.rekrutacja.transport.model.Delivery;
import com.rekrutacja.transport.model.enums.DeliveryStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryDTO {

    private Long idDelivery;
    private String itemName;
    private Double weight;
    private DeliveryStatus deliveryStatus;
    @NotNull
    private Long idTruck;
    @NotNull
    private Long idDriver;

    public static DeliveryDTO of(Delivery delivery) {
        DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                .idDelivery(delivery.getIdDelivery())
                .itemName(delivery.getItemName())
                .weight(delivery.getWeight())
                .deliveryStatus(delivery.getStatus())
                .build();

        if(delivery.getDriver() != null)
            deliveryDTO.setIdDriver(delivery.getDriver().getIdDriver());
        if(delivery.getTruck() != null)
            deliveryDTO.setIdTruck(delivery.getTruck().getIdTruck());
        // linijki powyzej ze wzgledu na mozliwosc usuniecia trucka/driver z bazy, mysle ze w praktyce raczej zamiast
        // usuwania ustawilibysmy status 'nieaktywny'

        return deliveryDTO;
    }

}
