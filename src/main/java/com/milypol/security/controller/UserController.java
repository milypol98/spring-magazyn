package com.milypol.security.controller;

import com.milypol.security.task.TaskService;
import com.milypol.security.user.User;
import com.milypol.security.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final TaskService taskService;

    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }
    //pracownicy admin
    @GetMapping
    public String showUserPage(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "workers/list";
    }
    //pracownicy admin
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("user", new User());
        return "users/edit";
    }
    //pracownicy admin // ten uzytkownik
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("task",taskService.getAllTasksByUserId(id));
        return "users/edit";
    }
    //pracownicy admin
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
