package com.milypol.security.productEvent;

import lombok.Getter;

@Getter
public enum ProductEventType {
    DELIVERY,           // dostawa do magazynu (UI: ukryte w tym ekranie)
    TRANSFER,           // magazyn -> auto
    RETURN,             // auto -> magazyn
    USED,               // zużycie w aucie
    LOST,               // utracone w aucie
    DELETE,             // korekta (usunięcie) w aucie
    TRANSFER_CAR_TO_CAR // auto -> auto
}
