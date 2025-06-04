package com.milypol.security.workerQualification;

import com.milypol.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class WorkerQualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String qualification;
    private String dateFrom;
    private String dateTo;
    @OneToOne
    private User user;

}
