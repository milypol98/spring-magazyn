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
import org.springframework.format.annotation.DateTimeFormat;

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
    private String description;
    private String comment;

    @NotBlank(message = "Nazwa jest wymagana.")
    @Column(nullable = false, length = 150)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;
    private Integer courseBefore;
    private Integer courseAfter;
    @ManyToMany
    @JoinTable(
            name = "task_user",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    @ManyToOne
    private Car car;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @AssertTrue(message = "Data 'od' nie może być po dacie 'do'.")
    public boolean isDateRangeValid() {
        if (dateFrom == null || dateTo == null) return true;
        return !dateFrom.isAfter(dateTo);
    }
}
