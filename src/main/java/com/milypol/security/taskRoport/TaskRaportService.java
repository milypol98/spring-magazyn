package com.milypol.security.taskRoport;

import java.util.List;

public interface TaskRaportService {
    List<TaskRaport> getAllTaskRaportsByTaskId(Integer taskId);
    TaskRaport saveTaskRaport(TaskRaport taskRaport);
    TaskRaport getTaskRaportById(Integer id);
    void deleteTaskRaport(Integer id);
}
