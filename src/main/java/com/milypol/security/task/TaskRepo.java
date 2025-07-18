package com.milypol.security.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Integer> {
    List<Task> findAllByUsers_IdOrderByDateFromDesc(Integer userId);

    List<Task> findAllByCars_id(Integer carId);


    List<Task> findAllByStatus(TaskStatus status);

}
