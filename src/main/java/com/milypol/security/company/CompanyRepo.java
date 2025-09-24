package com.milypol.security.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepo extends JpaRepository<Company, Long> {
    List<Company> findByNameContainingIgnoreCase(String namePart);

}