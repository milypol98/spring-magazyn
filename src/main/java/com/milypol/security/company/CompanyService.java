package com.milypol.security.company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company create(Company company);
    Company update(Long id, Company company);
    Optional<Company> findById(Long id);
    List<Company> findAll();
    void deleteById(Long id);
}