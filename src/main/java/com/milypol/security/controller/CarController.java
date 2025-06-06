package com.milypol.security.controller;

import com.milypol.security.car.Car;
import com.milypol.security.car.CarService;
import com.milypol.security.carCost.CarCost;
import com.milypol.security.carHistory.CarHistory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String showCarPage(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "car";
    }
    @GetMapping("/add")
    public String addForm( Model model) {
        model.addAttribute("car", new Car());
        return "cars/edit";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("car", carService.getCarById(id));
        model.addAttribute("car_cost", new CarCost());
        model.addAttribute("car_history", new CarHistory());
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
    @PostMapping( "history/save")
    public String saveHistoryCar(@ModelAttribute Car car) {
        carService.saveCar(car);
        return "redirect:/cars/edit";
    }
}
