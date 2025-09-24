package com.milypol.security.user;

import com.milypol.security.address.Address;
import com.milypol.security.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String list(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }
    @GetMapping("/{id}")
    public String info(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("tasks", taskService.getAllTasksByUserId(id));
        return "users/info";
    }
    @GetMapping("/edit")
    public String editCurrent(@AuthenticationPrincipal UserDetails principal, Model model) {
        var user = userService.getByEmail(principal.getUsername());
        model.addAttribute("updateRequest", userService.getUserForUpdate(user.getId()));
        model.addAttribute("tasks", taskService.getAllTasksByUserId(user.getId()));
        return "users/edit";
    }
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editUser(@PathVariable Integer id , Model model){
        model.addAttribute("updateAdminRequest", userService.getUserForAdminUpdate(id));
        model.addAttribute("roles", Role.values());
        model.addAttribute("tasks", taskService.getAllTasksByUserId(id));
        return "users/edit-admin";
    }
    @PostMapping("/save/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateUserByAdmin(
            @PathVariable Integer id,
            @Valid @ModelAttribute("updateAdminRequest") UpdateAdminRequest request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (bindingResult.hasErrors()) {
            User user = userService.getUserById(id);
            model.addAttribute("tasks", taskService.getAllTasksByUserId(user.getId()));
            model.addAttribute("roles", Role.values());
            return "users/edit-admin";
        }
        try {
            userService.updateUserByAdmin(id, request);
            redirectAttributes.addFlashAttribute("successMessage", "Dane użytkownika zostały zaktualizowane.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/users";
    }

    @PostMapping("/save")
    public String updateProfile(
            @AuthenticationPrincipal UserDetails principal,
            @Valid @ModelAttribute("updateRequest") UpdateRequest request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (bindingResult.hasErrors()) {
            User user = userService.getByEmail(principal.getUsername());
            model.addAttribute("tasks", taskService.getAllTasksByUserId(user.getId()));
            return "users/edit";
        }

        try {
            userService.updateUserProfile(principal.getUsername(), request);
            redirectAttributes.addFlashAttribute("successMessage", "Profil został zaktualizowany.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/users/edit";
    }
}
