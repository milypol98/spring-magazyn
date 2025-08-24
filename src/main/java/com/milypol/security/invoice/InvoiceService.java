package com.milypol.security.invoice;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    Optional<Invoice> findById(Long id);
    Invoice create(String invoiceNumber,
                   String name,
                   MultipartFile file,
                   String currency,
                   String totalNet,
                   String totalVat,
                   String totalGross,
                   Long companyId) throws IOException;

    Page<Invoice> list(Pageable pageable);
    Optional<Invoice> get(Long id);
    Resource loadFile(Long id);
    void delete(Long id);
    List<Invoice> getAllInvoicesByCompanyId(Long companyId);
    Page<Invoice> search(String q, LocalDate dateFrom, LocalDate dateTo, Long companyId, Pageable pageable);
}