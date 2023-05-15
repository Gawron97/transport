package com.rekrutacja.transport.DTO;

import com.rekrutacja.transport.model.Delivery;
import com.rekrutacja.transport.model.enums.DeliveryStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryDTO {

    private Long idDelivery;
    private String itemName;
    private double weight;
    private DeliveryStatus deliveryStatus;
    private Long idTruck;
    private Long idDriver;

    public static DeliveryDTO of(Delivery delivery) {
        return DeliveryDTO.builder()
                .idDelivery(delivery.getIdDelivery())
                .itemName(delivery.getItemName())
                .weight(delivery.getWeight())
                .deliveryStatus(delivery.getStatus())
                .build();
    }

}
