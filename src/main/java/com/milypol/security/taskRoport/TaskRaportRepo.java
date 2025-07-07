package com.milypol.security.taskRoport;

import com.milypol.security.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface TaskRaportRepo extends JpaRepository<TaskRaport, Integer> {
    List<TaskRaport> findAllByTask_Id(Integer taskId);
}
