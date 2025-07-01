package com.milypol.security.task;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TaskStatus {
    TO_BE_PACKED,
    IN_PROGRESS,
    TO_BE_UNPACKED,
    COMPLETED
}
