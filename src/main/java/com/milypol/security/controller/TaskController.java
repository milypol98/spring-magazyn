package com.milypol.security.controller;

import com.milypol.security.task.Task;
import com.milypol.security.task.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String showTaskPage(Model model){
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }
    @GetMapping("/add")
    public String addForm(Task task){
        taskService.saveTask(task);
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
