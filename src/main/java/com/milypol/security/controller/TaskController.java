package com.milypol.security.controller;

import com.milypol.security.car.CarService;
import com.milypol.security.place.PlaceService;
import com.milypol.security.product.ProductService;
import com.milypol.security.task.Task;
import com.milypol.security.task.TaskService;
import com.milypol.security.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final CarService carService;
    private final UserService userService;
    private final PlaceService placeService;
    private final ProductService productService;

    public TaskController(TaskService taskService, CarService carService, UserService userService, PlaceService placeService, ProductService productService) {
        this.taskService = taskService;
        this.carService = carService;
        this.userService = userService;
        this.placeService = placeService;
        this.productService = productService;
    }

    @GetMapping
    public String showTaskPage(Model model){
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks/list";
    }
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("task", new Task());
        model.addAttribute("allCars", carService.getAllCars());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allPlaces", placeService.getAllPlaces());
        model.addAttribute("allProducts", productService.getAllProducts());
        return "tasks/edit";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model){
        model.addAttribute("task", taskService.getTaskById(id));
        return "tasks/edit";
    }
    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Integer id){
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task){
        taskService.saveTask(task);
        return "redirect:/tasks";
    }
}
