package com.milypol.security.task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Integer id);
    Task saveTask(Task task);
    void deleteTask(Integer id);
    List<Task> getAllTasksByUserId(Integer userId);

    List<Task> getAllTasksByCarsId(Integer id);

    List<Task> getAllTasksByStatus(TaskStatus status);
    Optional<Task> getTaskByCarIdAndDate(Integer carId, LocalDate date);
}
