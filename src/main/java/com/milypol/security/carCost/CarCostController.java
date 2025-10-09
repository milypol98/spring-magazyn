package com.milypol.security.carCost;

import com.milypol.security.car.Car;
import com.milypol.security.car.CarService;
import com.milypol.security.invoice.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars/cost")
public class CarCostController {

    private final CarCostService carCostService;
    private final CarService carService;
    private final InvoiceService invoiceService;

    public CarCostController(CarCostService carCostService, CarService carService, InvoiceService invoiceService) {
        this.carCostService = carCostService;
        this.carService = carService;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/add/{carId}")
    public String addForm(@PathVariable Integer carId, Model model) {
        Car car = carService.getCarById(carId);
        CarCost carCost = new CarCost();
        carCost.setCar(car);

        model.addAttribute("car_cost", carCost);
        model.addAttribute("allInvoices", invoiceService.findAll());
        return "cars/cost-edit";
    }

    @GetMapping("/edit/{costId}")
    public String editForm(@PathVariable Integer costId, Model model) {
        model.addAttribute("car_cost", carCostService.getCarCostById(costId));
        model.addAttribute("allInvoices", invoiceService.findAll());
        return "cars/cost-edit";
    }

    @PostMapping("/save")
    public String saveCost(@Valid @ModelAttribute("car_cost") CarCost carCost, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allInvoices", invoiceService.findAll());
            return "cars/cost-edit";
        }
        carCostService.saveCarCost(carCost);
        return "redirect:/cars/info/" + carCost.getCar().getId();
    }
}