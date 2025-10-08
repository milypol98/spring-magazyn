package com.milypol.security.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskUpdateDto {
    private Integer id;
    private Integer carId;
    private TaskStatus status;
    private String comment;
    private Integer courseBefore;
    private Integer courseAfter;
}
