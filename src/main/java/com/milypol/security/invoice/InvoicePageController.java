package com.milypol.security.invoice;

import com.milypol.security.address.Address;
import com.milypol.security.company.Company;
import com.milypol.security.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoicePageController {

    private final InvoiceService invoiceService;
    private final CompanyService companyService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("invoices",invoiceService.findAll());
        return "invoices/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("companies", companyService.findAll());
        return "invoices/edit";
    }
    @PostMapping("/save")
    public String save(@RequestParam("nrFaktury") String nrFaktury,
                       @RequestParam("nazwa") String nazwa,
                       @RequestParam("plik") MultipartFile plik,
                       @RequestParam(value = "currency", required = false) String currency,
                       @RequestParam(value = "totalNet", required = false) String totalNet,
                       @RequestParam(value = "totalVat", required = false) String totalVat,
                       @RequestParam(value = "totalGross", required = false) String totalGross,
                       @RequestParam(value = "companyId", required = false) Long companyId,
                       Model model) {
        try {
            Invoice created = invoiceService.create(nrFaktury, nazwa, plik, currency, totalNet, totalVat, totalGross, companyId);
            return "redirect:/invoices/" + created.getId();
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("nrFaktury", nrFaktury);
            model.addAttribute("nazwa", nazwa);
            model.addAttribute("currency", currency);
            model.addAttribute("totalNet", totalNet);
            model.addAttribute("totalVat", totalVat);
            model.addAttribute("totalGross", totalGross);
            model.addAttribute("companyId", companyId);
            model.addAttribute("companies", companyService.findAll());
            return "invoices/edit";
        }
    }

    @GetMapping("/{id}")
    public String info(@PathVariable Long id, Model model) {
        return invoiceService.get(id)
                .map(inv -> {
                    model.addAttribute("invoice", inv);
                    // podstawowe dane firmy będą widoczne w widoku (np. nazwa)
                    return "invoices/info";
                })
                .orElseGet(() -> "redirect:/invoices");
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        invoiceService.delete(id);
        return "redirect:/invoices";
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        Optional<Invoice> opt = invoiceService.get(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Invoice inv = opt.get();
        if (inv.getStoredFileName() == null || inv.getStoredFileName().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String storageDir = System.getProperty("app.invoice.storage-dir", "uploads/invoices");
        Path filePath = Paths.get(storageDir).resolve(inv.getStoredFileName()).normalize();

        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            String downloadName = Optional.ofNullable(inv.getOriginalFileName()).orElse(inv.getStoredFileName());
            String contentType = Optional.ofNullable(inv.getContentType()).orElse(MediaType.APPLICATION_OCTET_STREAM_VALUE);

            long contentLength = -1L;
            try { contentLength = Files.size(filePath); } catch (Exception ignored) {}

            ResponseEntity.BodyBuilder builder = ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + downloadName.replace("\"", "") + "\"");

            if (contentLength >= 0) {
                builder.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(contentLength));
            }
            return builder.body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}