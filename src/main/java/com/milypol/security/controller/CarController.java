package com.milypol.security.controller;

import com.milypol.security.car.Car;
import com.milypol.security.car.CarService;
import com.milypol.security.carCost.CarCost;
import com.milypol.security.carCost.CarCostService;

import com.milypol.security.task.TaskService;
import com.milypol.security.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CarCostService carCostService;
    private final TaskService taskService;
    private final UserService userService;

    public CarController(CarService carService, CarCostService carCostService, TaskService taskService, UserService userService) {
        this.carService = carService;
        this.carCostService = carCostService;
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public String showCarPage(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "cars/list";
    }
    @GetMapping("/add")
    public String addForm( Model model) {
        model.addAttribute("car", new Car());
        return "cars/edit";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        model.addAttribute("tasks", taskService.getAllTasksByCarsId(id));
        return "cars/edit";
    }

    @PostMapping("/delete/{id}")
    public String deleteCar( @PathVariable Integer id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }
    @PostMapping( "/save")
    public String saveCar(@ModelAttribute Car car) {
        carService.saveCar(car);
        return "redirect:/cars";
    }
    @GetMapping("cost/add/{carId}")
    public String carCostAdd(@PathVariable Integer carId, Model model) {
        CarCost carCost = new CarCost();
        carCost.setCar(carService.getCarById(carId));
        model.addAttribute("car_cost", carCost);
        return "cars/costEdit";
    }
    @GetMapping("cost/edit/{id}")
    public String carCostEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("car_cost", carCostService.getCarCostById(id));
        return "cars/costEdit";
    }
    @PostMapping( "cost/save")
    public String saveCostCar(@ModelAttribute CarCost carCost) {
        carCostService.saveCarCost(carCost);
        return "redirect:/cars/edit/" + carCost.getCar().getId();
    }
    @PostMapping("/cost/delete/{id}")
    public String deleteCostCar(@PathVariable Integer id){
        carCostService.deleteCarCost(id);
        return "redirect:/cars/edit";
    }

}
