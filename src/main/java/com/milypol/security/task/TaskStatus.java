package com.milypol.security.task;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TaskStatus {

    BEFORE_TASK("Przed"),
    IN_PROGRESS("W trakcie"),
    COMPLETED("Zakończone");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }
}
