package com.milypol.security.task;

import org.springframework.stereotype.Service;

import java.util.List;

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
        return taskRepo.findAllByUsers_Id(userId);
    }

    @Override
    public List<Task> getAllTasksByCarId(Integer carId) {
        return taskRepo.findAllByCar_id(carId);
    }

    @Override
    public List<Task> getAllTasksByProductId(Integer id) {
        return taskRepo.findAllByProducts_Id(id);
    }

}
