package com.milypol.security.invoice;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {
    List<Invoice> findAll();
    Invoice findById(Long id);

    void saveInvoice(Invoice invoice, MultipartFile file) throws IOException;

    Resource loadFileAsResource(String filename);

    Page<Invoice> list(Pageable pageable);
    void delete(Long id);
    List<Invoice> getAllInvoicesByCompanyId(Long companyId);
    Page<Invoice> search(String q, LocalDate dateFrom, LocalDate dateTo, Long companyId, Pageable pageable);
}