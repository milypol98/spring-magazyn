package com.milypol.security.user;

import com.milypol.security.address.Address;
import com.milypol.security.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "workers/list";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id , Model model){
        var user = userService.getUserById(id);
        if (user.getAddress() == null) user.setAddress(new Address());
        model.addAttribute("user", user);
        model.addAttribute("tasks", taskService.getAllTasksByUserId(id));
        return "settings/edit";
    }

    @GetMapping("/edit")
    public String editCurrent(@AuthenticationPrincipal UserDetails principal, Model model) {
        var user = userService.getByEmail(principal.getUsername());
        if (user.getAddress() == null) user.setAddress(new Address());
        model.addAttribute("user", user);
        model.addAttribute("tasks", taskService.getAllTasksByUserId(user.getId()));
        return "settings/edit";
    }

    @PostMapping("/edit")
    public String update(@AuthenticationPrincipal UserDetails principal,
                         @Valid @ModelAttribute("user") User form,
                         BindingResult bindingResult,
                         @RequestParam(value = "newPassword", required = false) String newPassword,
                         @RequestParam(value = "confirmPassword", required = false) String confirmPassword,
                         RedirectAttributes ra,
                         Model model) {

        // Ustal właściwe id: z formularza lub bieżącego zalogowanego
        Integer targetId = form.getId();
        if (targetId == null) {
            var current = userService.getByEmail(principal.getUsername());
            targetId = current.getId();
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("tasks", taskService.getAllTasksByUserId(targetId));
            return "settings/edit";
        }

        if (newPassword != null && !newPassword.isBlank()) {
            if (!newPassword.equals(confirmPassword)) {
                bindingResult.rejectValue("password", "password.mismatch", "Hasła nie są zgodne");
            } else if (newPassword.length() < 8) {
                bindingResult.rejectValue("password", "password.tooShort", "Hasło musi mieć co najmniej 8 znaków");
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("tasks", taskService.getAllTasksByUserId(targetId));
            return "settings/edit";
        }

        // Tu można dodać sprawdzenie uprawnień, jeśli edytujemy kogoś innego niż siebie
        userService.updateUser(targetId, form, newPassword, passwordEncoder);

        ra.addFlashAttribute("message", "Dane użytkownika zostały zaktualizowane");

        // Jeśli edytowaliśmy siebie -> wróć do /users/edit, jeśli innego -> do /users/edit/{id}
        var isSelf = form.getId() == null || form.getId().equals(targetId);
        return isSelf ? "redirect:/users/edit" : "redirect:/users/edit/" + targetId;
    }
}
