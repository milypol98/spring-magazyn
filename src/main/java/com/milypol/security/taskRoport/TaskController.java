package com.milypol.security.taskRoport;

import com.milypol.security.car.CarService;
import com.milypol.security.cart.CartService;
import com.milypol.security.product.ProductService;
import com.milypol.security.productEvent.ProductEventService;
import com.milypol.security.task.Task;
import com.milypol.security.task.TaskService;
import com.milypol.security.task.TaskStatus;
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

    private final ProductService productService;
    private final CartService cartService;
    private final TaskRaportService taskRaportService;
    private final ProductEventService productEventService;

    public TaskController(TaskService taskService, CarService carService, UserService userService, ProductService productService, CartService cartService, TaskRaportService taskRaportService, ProductEventService productEventService) {
        this.taskService = taskService;
        this.carService = carService;
        this.userService = userService;

        this.productService = productService;
        this.cartService = cartService;
        this.taskRaportService = taskRaportService;
        this.productEventService = productEventService;
    }

    @GetMapping
    public String showTaskPage(Model model){
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("statusCompleted", TaskStatus.COMPLETED);
        return "tasks/list";
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
        model.addAttribute("taskRaports", taskRaportService.getAllTaskRaportsByTaskId(id));
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
    public String saveTask(@ModelAttribute Task task){
        taskService.saveTask(task);
        return "redirect:/tasks";
    }
    @GetMapping("/raports/add/{taskId}")
    public String addTaskRaports(@PathVariable Integer taskId, Model model){
        TaskRaport taskRaport = new TaskRaport();
        taskRaport.setTask(taskService.getTaskById(taskId));
        model.addAttribute("taskRaport", taskRaport);
        return "tasks/raportEdit";
    }
    @PostMapping("/raports/save")
    public String saveTaskRaport(@ModelAttribute TaskRaport taskRaport){
        taskRaportService.saveTaskRaport(taskRaport);
        return "redirect:/tasks/edit/" + taskRaport.getTask().getId();
    }
}
