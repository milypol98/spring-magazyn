package com.milypol.security.controller;

import com.milypol.security.task.TaskService;
import com.milypol.security.user.User;
import com.milypol.security.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;
    private final TaskService taskService;

    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }
    //pracownicy admin
    @GetMapping("/users")
    public String showUserPage(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "workers/list";
    }
    //pracownicy admin // ten uzytkownik
    @GetMapping("/profile")
    public String editForm(Model model, Authentication authentication){
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", userService.getUserById(user.getId()));
        model.addAttribute("task",taskService.getAllTasksByUserId(user.getId()));
        return "settings/edit";
    }
    //pracownicy admin
//    @PostMapping("/delete/{id}")
//    public String deleteUser(@PathVariable Integer id){
//        userService.deleteUser(id);
//        return "redirect:/users";
//    }

}
