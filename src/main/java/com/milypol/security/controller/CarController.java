package com.milypol.security.controller;

import com.milypol.security.car.Car;
import com.milypol.security.car.CarService;
import com.milypol.security.carCost.CarCost;
import com.milypol.security.carCost.CarCostService;
import com.milypol.security.carCost.CarCostServiceImpl;
import com.milypol.security.carHistory.CarHistory;
import com.milypol.security.carHistory.CarHistoryService;
import com.milypol.security.product.Product;
import com.milypol.security.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final CarCostService carCostService;
    private final CarHistoryService carHistoryService;
    private final ProductService productService;

    public CarController(CarService carService, CarCostService carCostService, CarHistoryService carHistoryService, ProductService productService) {
        this.carService = carService;
        this.carCostService = carCostService;
        this.carHistoryService = carHistoryService;
        this.productService = productService;
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
    @GetMapping("cost/edit/{id}")
    public String carCostEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("car_cost", carCostService.getCarCostById(id));
        return "cars/cost";
    }
    @GetMapping("history/edit/{id}")
    public String carHistoryEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("car_histowy", carHistoryService.getCarHistoryById(id));
        return "cars/history";
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
