package com.milypol.security.controller;

import com.milypol.security.car.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/car")
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
}
