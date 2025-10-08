package com.milypol.security.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Integer id);
    void saveTask(Task task);
    void deleteTask(Integer id);
    List<Task> getAllTasksByUserId(Integer userId);

    List<Task> getAllTasksByCarId(Integer id);

    List<Task> getAllTasksByStatus(TaskStatus status);
    Optional<Task> getTaskByCarIdAndDate(Integer carId, LocalDate date);
    Page<Task> search(String q, TaskStatus status, Integer userId, LocalDate fromDate, LocalDate toDate, Pageable pageable);
    void updateTaskAndCarCourse(TaskUpdateDto dto);
}
