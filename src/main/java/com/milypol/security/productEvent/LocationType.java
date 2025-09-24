package com.milypol.security.productEvent;

import lombok.Getter;

@Getter
public enum LocationType {
    WAREHOUSE("Magazyn"),
    CAR("Auta"),
    TASK("Zadanie");

    private final String name;

    LocationType(String name) {
        this.name = name;
    }
}
