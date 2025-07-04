package com.milypol.security.task;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TaskStatus {

    TO_BE_PACKED("Do spakowania"),
    IN_PROGRESS("W trakcie"),
    TO_BE_UNPACKED("Do rozpakowania"),
    COMPLETED("Zakończone");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }
}
