package com.milypol.security.task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Integer id);
    Task saveTask(Task task);
    void deleteTask(Integer id);
    List<Task> getAllTasksByUserId(Integer userId);

    List<Task> getAllTasksByCarsId(Integer id);

    List<Task> getAllTasksByProductId(Integer id);
}
