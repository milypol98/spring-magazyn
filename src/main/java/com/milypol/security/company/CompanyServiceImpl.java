package com.milypol.security.company;

import com.milypol.security.company.Company;
import com.milypol.security.company.CompanyRepo;
import com.milypol.security.company.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepo repository;

    @Override
    public Company create(Company company) {
        return repository.save(company);
    }

    @Override
    public Company update(Long id, Company company) {
        Company existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Firma nie znaleziona: " + id));
        existing.setName(company.getName());
        existing.setPhoneNumber(company.getPhoneNumber());
        existing.setEmail(company.getEmail());
        existing.setWebsite(company.getWebsite());
        existing.setNip(company.getNip());
        existing.setRegon(company.getRegon());
        existing.setDescription(company.getDescription());
        existing.setAddress(company.getAddress());
        return repository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Company> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> findAll() {
        return repository.findAll();
    }


    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}