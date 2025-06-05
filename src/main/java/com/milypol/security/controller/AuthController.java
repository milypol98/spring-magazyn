package com.milypol.security.controller;
import com.milypol.security.user.RegistrationRequest;
import com.milypol.security.user.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;

    // GET /register – wyświetlenie formularza rejestracji
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "register"; // templates/register.html
    }

    // POST /register – przetwarzanie rejestracji
    @PostMapping("/register")
    public String processRegistration(
            @ModelAttribute("registrationRequest") @Valid RegistrationRequest request,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            // Błędy walidacji (np. niepoprawny e-mail, zbyt krótkie hasło itp.)
            return "register";
        }
        try {
            registrationService.register(request);
        } catch (RuntimeException e) {
            model.addAttribute("registrationError", e.getMessage());
            return "register";
        }
        // Po poprawnej rejestracji przekierowujemy do logowania z informacją
        return "redirect:/login?registered=true";
    }

    // GET /login – wyświetlenie formularza logowania
    @GetMapping("/login")
    public String showLoginForm(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "registered", required = false) String registered,
            Model model
    ) {
        if (error != null) {
            model.addAttribute("loginError", "Niepoprawny e-mail lub hasło.");
        }
        if (logout != null) {
            model.addAttribute("logoutMsg", "Zostałeś wylogowany pomyślnie.");
        }
        if (registered != null) {
            model.addAttribute("registeredMsg", "Rejestracja zakończona sukcesem. Możesz się teraz zalogować.");
        }
        return "login"; // templates/login.html
    }

    // GET /dashboard – strona chroniona
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard"; // templates/dashboard.html
    }
}