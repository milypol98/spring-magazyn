package com.milypol.security.invoice;

import com.milypol.security.company.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByCompany_Id(Long companyId);

    @Query("""
           SELECT i FROM Invoice i
             LEFT JOIN i.company c
           WHERE (:q IS NULL OR LOWER(i.name) LIKE LOWER(CONCAT('%', :q, '%'))
                              OR LOWER(i.invoiceNumber) LIKE LOWER(CONCAT('%', :q, '%'))
                              OR (c IS NOT NULL AND LOWER(c.name) LIKE LOWER(CONCAT('%', :q, '%'))))
             AND (:from IS NULL OR i.uploadedAt >= :from)
             AND (:to   IS NULL OR i.uploadedAt <= :to)
             AND (:companyId IS NULL OR (c IS NOT NULL AND c.id = :companyId))
           ORDER BY i.uploadedAt DESC, i.id DESC
           """)
    Page<Invoice> search(@Param("q") String q,
                         @Param("from") Instant from,
                         @Param("to") Instant to,
                         @Param("companyId") Long companyId,
                         Pageable pageable);
}