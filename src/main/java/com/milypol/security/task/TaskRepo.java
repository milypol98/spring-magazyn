package com.milypol.security.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task, Integer> {
    List<Task> findAllByUsers_IdOrderByDateFromDesc(Integer userId);
    List<Task> findAllByCar_id(Integer carId);
    List<Task> findAllByStatus(TaskStatus status);


    @Query("SELECT t FROM Task t JOIN t.car c " +
            "WHERE c.id = :carId " +
            "AND :now BETWEEN t.dateFrom AND t.dateTo")
    Optional<Task> findTaskByCarIdAndCurrentDateBetween(@Param("carId") Integer carId, @Param("now") LocalDate date);

    @Query(
            value = """
                SELECT DISTINCT t FROM Task t
                LEFT JOIN t.users u
                WHERE (:q IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', :q, '%')))
                  AND (:status IS NULL OR t.status = :status)
                  AND (:userId IS NULL OR u.id = :userId)
                  AND (:fromDate IS NULL OR t.dateFrom >= :fromDate)
                  AND (:toDate IS NULL OR t.dateTo <= :toDate)
                """,
            countQuery = """
                SELECT COUNT(DISTINCT t) FROM Task t
                LEFT JOIN t.users u
                WHERE (:q IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', :q, '%')))
                  AND (:status IS NULL OR t.status = :status)
                  AND (:userId IS NULL OR u.id = :userId)
                  AND (:fromDate IS NULL OR t.dateFrom >= :fromDate)
                  AND (:toDate IS NULL OR t.dateTo <= :toDate)
                """
    )
    Page<Task> search(
            @Param("q") String q,
            @Param("status") TaskStatus status,
            @Param("userId") Integer userId,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            Pageable pageable
    );
}
