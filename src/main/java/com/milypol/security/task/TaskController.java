package com.milypol.security.task;

import com.milypol.security.car.CarService;
import com.milypol.security.product.ProductService;
import com.milypol.security.productEvent.ProductEventService;
import com.milypol.security.user.UserService;
import com.milypol.security.cart.CartService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final CarService carService;
    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final ProductEventService productEventService;

    public TaskController(TaskService taskService, CarService carService, UserService userService,
                          ProductService productService, CartService cartService, ProductEventService productEventService) {
        this.taskService = taskService;
        this.carService = carService;
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
        this.productEventService = productEventService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks/list";
    }
    @GetMapping("/{id}")
    public String details(@PathVariable Integer id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        // Zużycia: USED -> TASK, kod lokacji = id zadania
        model.addAttribute("usedEvents", productEventService.findUsedForTask(id));
        return "tasks/info";
    }


    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("task", new Task());
        model.addAttribute("allCars", carService.getAllCars());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allProducts", productService.getAllProducts());
        model.addAttribute("allCarts", cartService.getAllCarts());
        return "tasks/edit";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model){
        model.addAttribute("task", taskService.getTaskById(id));
        model.addAttribute("allCars", carService.getAllCars());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allProducts", productService.getAllProducts());
        model.addAttribute("allCarts", cartService.getAllCarts());
        return "tasks/edit";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Integer id){
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/save")
    public String saveTask(@Valid @ModelAttribute Task task){
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

}
