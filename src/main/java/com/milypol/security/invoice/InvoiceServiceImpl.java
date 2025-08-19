package com.milypol.security.invoice;

import com.milypol.security.company.Company;
import com.milypol.security.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepo invoiceRepo;
    private final CompanyService companyService;
    // ... ewentualne inne zależności (np. storage)

    @Override
    public Invoice create(String invoiceNumber,
                          String name,
                          MultipartFile file,
                          String currency,
                          String totalNet,
                          String totalVat,
                          String totalGross,
                          Long companyId) throws IOException {

        // Walidacje podstawowe (np. duplikat numeru)
        if (invoiceRepo.existsByInvoiceNumber(invoiceNumber)) {
            throw new IllegalArgumentException("Faktura o numerze " + invoiceNumber + " już istnieje");
        }

        Invoice inv = new Invoice();
        inv.setInvoiceNumber(invoiceNumber);
        inv.setName(name);
        inv.setCurrency(currency);
        if (totalNet != null && !totalNet.isBlank()) inv.setTotalNet(new BigDecimal(totalNet.replace(",", ".")));
        if (totalVat != null && !totalVat.isBlank()) inv.setTotalVat(new BigDecimal(totalVat.replace(",", ".")));
        if (totalGross != null && !totalGross.isBlank()) inv.setTotalGross(new BigDecimal(totalGross.replace(",", ".")));
        inv.setUploadedAt(Instant.now());

        // Zapis pliku (tu tylko szkic — dostosuj do swojej logiki)
        if (file != null && !file.isEmpty()) {
            String storedName = /* twoja logika zapisu pliku */ file.getOriginalFilename();
            inv.setStoredFileName(storedName);
            inv.setOriginalFileName(file.getOriginalFilename());
            inv.setContentType(file.getContentType());
            inv.setFileSize(file.getSize());
        }

        // Przypisanie firmy (opcjonalne)
        if (companyId != null) {
            Company company = companyService.findById(companyId)
                    .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono firmy o id " + companyId));
            inv.setCompany(company);
        }

        return invoiceRepo.save(inv);
    }

    @Override
    public Page<Invoice> list(Pageable pageable) {
        return invoiceRepo.findAll(pageable);
    }

    @Override
    public Optional<Invoice> get(Long id) {
        return invoiceRepo.findById(id);
    }

    @Override
    public Resource loadFile(Long id) {
        // ...
        throw new UnsupportedOperationException("Not implemented here");
    }

    @Override
    public void delete(Long id) {
        invoiceRepo.deleteById(id);
    }

    @Override
    public List<Invoice> getAllInvoicesByCompanyId(Long companyId) {
        return invoiceRepo.findAllByCompany_Id(companyId);
    }

    @Override
    public Page<Invoice> search(String q, LocalDate dateFrom, LocalDate dateTo, Long companyId, Pageable pageable) {
        return invoiceRepo.search(
                q,
                dateFrom != null ? dateFrom.atStartOfDay().toInstant(java.time.ZoneOffset.UTC) : null,
                dateTo != null ? dateTo.plusDays(1).atStartOfDay().toInstant(java.time.ZoneOffset.UTC) : null,
                companyId,
                pageable
        );
    }
}