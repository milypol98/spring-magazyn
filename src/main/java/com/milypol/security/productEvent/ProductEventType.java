package com.milypol.security.productEvent;

import lombok.Getter;

@Getter
public enum ProductEventType {
    DELIVERY("Dostawa do magazynu"),
    TRANSFER("Transfer z magazynu do auta"),
    RETURN("Zwrot z auta do magazynu"),
    USED("Zużycie w zadaniu"),
    LOST_IN_CAR("Utracone w aucie"),
    LOST_IN_WAREHOUSES("Utracone w magazynie"),
    TRANSFER_CAR_TO_CAR("Transfer z auta do auta");

    private final String name;

    ProductEventType(String name) {
        this.name = name;
    }
}

