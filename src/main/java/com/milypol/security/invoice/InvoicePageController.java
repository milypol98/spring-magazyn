package com.milypol.security.invoice;


import com.milypol.security.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/invoices")
public class InvoicePageController {

    private final InvoiceService invoiceService;
    private final CompanyService companyService;

    public InvoicePageController(InvoiceService invoiceService, CompanyService companyService) {
        this.invoiceService = invoiceService;
        this.companyService = companyService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("invoices",invoiceService.findAll());
        return "invoices/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("invoice", new Invoice());
        model.addAttribute("companies", companyService.findAll());
        return "invoices/edit";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("invoice", invoiceService.findById(id));
        model.addAttribute("companies", companyService.findAll());
        return "invoices/edit";
    }
    @PostMapping("/save")
    public String saveInvoice(@ModelAttribute Invoice invoice,
                              @RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes) {
        try {
            invoiceService.saveInvoice(invoice, file);
            redirectAttributes.addFlashAttribute("success", "Faktura została pomyślnie zapisana!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Wystąpił błąd podczas zapisu faktury.");
            return "redirect:/invoices/add";
        }
        return "redirect:/invoices";
    }
    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        // Serwis sam rzuci wyjątek, jeśli faktura nie zostanie znaleziona
        Invoice invoice = invoiceService.findById(id);

        Resource resource = invoiceService.loadFileAsResource(invoice.getStoredFileName());
        String contentType = invoice.getContentType() != null ? invoice.getContentType() : "application/octet-stream";
        String headerValue = "attachment; filename=\"" + invoice.getOriginalFileName() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);

    }

    @GetMapping("/{id}")
    public String info(@PathVariable Long id, Model model) {
        try {
            // Spróbuj znaleźć fakturę - serwis zwróci obiekt lub rzuci wyjątek
            Invoice invoice = invoiceService.findById(id);
            model.addAttribute("invoice", invoice);
            return "invoices/info";
        } catch (RuntimeException e) {
            System.err.println("Nie znaleziono faktury o ID: " + id);
            return "redirect:/invoices";
        }
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        invoiceService.delete(id);
        return "redirect:/invoices";
    }


}