package com.milypol.security.invoice;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepo invoiceRepo;
    private final Path fileStorageLocation;

    public InvoiceServiceImpl(InvoiceRepo invoiceRepo, @Value("${file.upload-dir}") String uploadDir) {
        this.invoiceRepo = invoiceRepo;
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Nie można utworzyć folderu do przechowywania plików.", ex);
        }
    }
    @Override
    public List<Invoice> findAll() {
        return invoiceRepo.findAll();
    }

    @Override
    public Invoice findById(Long id) {
        return invoiceRepo.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    @Override
    public void saveInvoice(Invoice invoice, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileExtension = StringUtils.getFilenameExtension(originalFileName);
            String storedFileName = UUID.randomUUID() + "." + fileExtension;

            Path targetLocation = this.fileStorageLocation.resolve(storedFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            invoice.setOriginalFileName(originalFileName);
            invoice.setStoredFileName(storedFileName);
            invoice.setContentType(file.getContentType());
            invoice.setFileSize(file.getSize());
            invoice.setUploadedAt(Instant.now());
        }
        invoiceRepo.save(invoice);
    }
    @Override
    public Resource loadFileAsResource(String filename) {
        try {
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Plik nie został znaleziony: " + filename);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Błąd podczas odczytu pliku: " + filename, ex);
        }
    }

    @Override
    public Page<Invoice> list(Pageable pageable) {
        return invoiceRepo.findAll(pageable);
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