package com.milypol.security.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Integer> {
    List<Task> findAllByUsers_Id(Integer userId);

    List<Task> findAllByCar_id(Integer carId);

    List<Task> findAllByProducts_Id(Integer productsId);
}
