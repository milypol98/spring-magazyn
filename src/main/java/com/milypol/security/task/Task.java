package com.milypol.security.task;

import com.milypol.security.car.Car;
import com.milypol.security.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 255, message = "Opis może mieć maksymalnie 255 znaków.")
    @Column(length = 255)
    private String description;

    @NotBlank(message = "Nazwa jest wymagana.")
    @Column(nullable = false, length = 150)
    private String name;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    @ManyToMany
    @JoinTable(
            name = "task_user",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "task_car",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private List<Car> cars;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @AssertTrue(message = "Data 'od' nie może być po dacie 'do'.")
    public boolean isDateRangeValid() {
        if (dateFrom == null || dateTo == null) return true;
        return !dateFrom.isAfter(dateTo);
    }
}
