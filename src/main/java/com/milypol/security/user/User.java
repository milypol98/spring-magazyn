package com.milypol.security.user;

import com.milypol.security.address.Address;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;
    private String lastName;

    @Column(nullable = false)
    private String password;

    private Long phone;
    private String description;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Tak samo jak w Company: adres jako komponent osadzony
    @Valid
    @Embedded
    private Address address;
}
