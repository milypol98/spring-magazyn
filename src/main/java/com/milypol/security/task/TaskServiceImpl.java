package com.milypol.security.task;

import com.milypol.security.car.Car;
import com.milypol.security.car.CarRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final CarRepo carRepo;

    public TaskServiceImpl(TaskRepo taskRepo, CarRepo carRepo) {
        this.taskRepo = taskRepo;
        this.carRepo = carRepo;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepo.findAll(Sort.by(Sort.Direction.DESC, "dateFrom"));
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public void saveTask(Task task) {
        taskRepo.save(task);
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
    public List<Task> getAllTasksByCarId(Integer carId) {
        return taskRepo.findAllByCar_id(carId);
    }



    @Override
    public List<Task> getAllTasksByStatus(TaskStatus status) {
        return taskRepo.findAllByStatus(status);
    }

    @Override
    public Optional<Task> getTaskByCarIdAndDate(Integer carId, LocalDate date) {
        return taskRepo.findTaskByCarIdAndCurrentDateBetween(carId, date);
    }
    @Override
    @Transactional(readOnly = true)
    public Page<Task> search(String q, TaskStatus status, Integer userId, LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        String query = (q == null || q.isBlank()) ? null : q.trim();
        return taskRepo.search(query, status, userId, fromDate, toDate, pageable);
    }
    @Override
    @Transactional
    public void updateTaskAndCarCourse(TaskUpdateDto dto) {
        Task task = taskRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + dto.getId()));

        task.setStatus(dto.getStatus());
        if (dto.getComment() != null && !dto.getComment().trim().isEmpty()) {
            task.setComment(dto.getComment());
        }
        if (dto.getCourseBefore() != null) {
            task.setCourseBefore(dto.getCourseBefore());
        }
        if (dto.getCourseAfter() != null) {
            task.setCourseAfter(dto.getCourseAfter());
        }

        Integer newCourse = dto.getCourseAfter() != null ? dto.getCourseAfter() : dto.getCourseBefore();
        if (dto.getCarId() != null && newCourse != null) {
            Car carToUpdate = carRepo.findById(dto.getCarId())
                    .orElseThrow(() -> new RuntimeException("Car not found with id: " + dto.getCarId()));
            carToUpdate.setCourse(newCourse);
            carRepo.save(carToUpdate);
        }
        taskRepo.save(task);
    }
}
