package com.rekrutacja.transport.model;

import com.rekrutacja.transport.DTO.DeliveryDTO;
import com.rekrutacja.transport.model.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelivery;
    private String itemName;
    private Double weight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDriver")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Driver driver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTruck")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Truck truck;

    @Enumerated(value = EnumType.STRING)
    private DeliveryStatus status;

    public static Delivery of(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setItemName(deliveryDTO.getItemName());
        delivery.setWeight(deliveryDTO.getWeight());
        delivery.setStatus(deliveryDTO.getDeliveryStatus());
        return delivery;
    }

}
