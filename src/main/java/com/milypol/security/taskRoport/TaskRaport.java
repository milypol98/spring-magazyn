package com.milypol.security.taskRoport;

import com.milypol.security.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class TaskRaport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime date;
    private LocalDateTime dateComeIn;
    private String description;
    private Integer Hour;
    @ManyToOne
    private Task task;
}
