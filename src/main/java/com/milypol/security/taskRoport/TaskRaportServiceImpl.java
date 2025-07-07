package com.milypol.security.taskRoport;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskRaportServiceImpl implements TaskRaportService {
    private final TaskRaportRepo taskRaportRepo;

    public TaskRaportServiceImpl(TaskRaportRepo taskRaportRepo) {
        this.taskRaportRepo = taskRaportRepo;
    }

    @Override
    public List<TaskRaport> getAllTaskRaportsByTaskId(Integer taskId) {
        return taskRaportRepo.findAllByTask_Id(taskId);
    }

    @Override
    public TaskRaport saveTaskRaport(TaskRaport taskRaport) {
        return taskRaportRepo.save(taskRaport);
    }

    @Override
    public TaskRaport getTaskRaportById(Integer id) {
        return taskRaportRepo.findById(id).orElseThrow(() -> new RuntimeException("TaskRaport not found"));
    }

    @Override
    public void deleteTaskRaport(Integer id) {
        taskRaportRepo.deleteById(id);

    }
}
