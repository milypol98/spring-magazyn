package com.milypol.security.task;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepo.deleteById(id);
    }

    @Override
    public List<Task> getAllTasksByUserId(Integer userId) {
        return taskRepo.findAllByUsers_IdOrderByDateFromDesc(userId);
    }

    @Override
    public List<Task> getAllTasksByCarsId(Integer carId) {
        return taskRepo.findAllByCars_id(carId);
    }



    @Override
    public List<Task> getAllTasksByStatus(TaskStatus status) {
        return taskRepo.findAllByStatus(status);
    }

    @Override
    public Optional<Task> getTaskByCarIdAndDate(Integer carId, LocalDate date) {
        return taskRepo.findTaskByCarIdAndCurrentDateBetween(carId, date);
    }



}
