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
    public String list(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            Model model
    ) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateFrom"));
        Page<Task> tasksPage = taskService.search(q, status, userId, from, to, pageable);

        model.addAttribute("page", tasksPage);
        model.addAttribute("tasks", tasksPage.getContent());
        model.addAttribute("q", q);
        model.addAttribute("status", status);
        model.addAttribute("userId", userId);
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        model.addAttribute("size", size);
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("statusValues", TaskStatus.values());
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
