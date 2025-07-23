package com.milypol.security.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task, Integer> {
    List<Task> findAllByUsers_IdOrderByDateFromDesc(Integer userId);

    List<Task> findAllByCars_id(Integer carId);


    List<Task> findAllByStatus(TaskStatus status);

    @Query("SELECT t FROM Task t JOIN t.cars c " +
            "WHERE c.id = :carId " +
            "AND :now BETWEEN t.dateFrom AND t.dateTo")
    Optional<Task> findTaskByCarIdAndCurrentDateBetween(@Param("carId") Integer carId, @Param("now") LocalDate date);}
