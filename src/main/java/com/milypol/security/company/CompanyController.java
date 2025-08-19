package com.milypol.security.company;

import com.milypol.security.address.Address;
import com.milypol.security.invoice.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final InvoiceService invoiceService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("companies", companyService.findAll());

        return "companies/list";
    }

    @GetMapping("/add")
    public String createForm(Model model) {
        Company company = new Company();
        company.setAddress(Address.builder().country("Polska").build());
        model.addAttribute("company", company);
        return "companies/edit";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("company") Company company,
                         BindingResult bindingResult,
                         RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            return "companies/edit";
        }
        companyService.create(company);
        ra.addFlashAttribute("message", "Firma została utworzona");
        return "redirect:/companies";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        Company company = companyService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono firmy o id " + id));
        model.addAttribute("company", company);
        model.addAttribute("invoices", invoiceService.getAllInvoicesByCompanyId(id));
        return "companies/info";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Company company = companyService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono firmy o id " + id));
        if (company.getAddress() == null) {
            company.setAddress(new Address());
        }
        model.addAttribute("company", company);
        return "companies/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("company") Company company,
                         BindingResult bindingResult,
                         RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            return "companies/edit";
        }
        companyService.update(id, company);
        ra.addFlashAttribute("message", "Dane firmy zostały zaktualizowane");
        return "redirect:/companies";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        companyService.deleteById(id);
        ra.addFlashAttribute("message", "Firma została usunięta");
        return "redirect:/companies";
    }
}