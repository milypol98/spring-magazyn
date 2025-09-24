package com.milypol.security.config;
import com.milypol.security.user.RegistrationRequest;
import com.milypol.security.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "users/add";
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public String processRegistration(
            @ModelAttribute("registrationRequest") @Valid RegistrationRequest request,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "users/add";
        }

        try {
            userService.register(request);
        } catch (RuntimeException e) {
            model.addAttribute("formError", e.getMessage());
            return "users/add";
        }
        return "redirect:/users?registered=true";
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
        return "login"; // templates/login.html
    }

    // GET /dashboard – strona chroniona
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard"; // templates/dashboard.html
    }
}