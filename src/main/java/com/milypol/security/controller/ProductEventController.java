package com.milypol.security.controller;

import com.milypol.security.car.CarService;
import com.milypol.security.product.ProductService;
import com.milypol.security.productEvent.LocationType;
import com.milypol.security.productEvent.ProductEvent;
import com.milypol.security.productEvent.ProductEventService;
import com.milypol.security.productEvent.ProductEventType;
import com.milypol.security.task.Task;
import com.milypol.security.task.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class ProductEventController {
    private final ProductEventService productEventService;
    private final ProductService productService;
    private final TaskService taskService;
    private final CarService carService;

    public ProductEventController(ProductEventService productEventService, ProductService productService, TaskService taskService, CarService carService) {
        this.productEventService = productEventService;
        this.productService = productService;
        this.taskService = taskService;
        this.carService = carService;
    }
    @GetMapping("/add/{productId}")
    public String addEventPage(@PathVariable Integer productId, Model model){
        ProductEvent productEvent = new ProductEvent();
        productEvent.setProduct(productService.getProductById(productId));
        model.addAttribute("product", productService.getProductById(productId));
        model.addAttribute("productEvent", productEvent);
        return "/events/edit";
    }
    @GetMapping("/edit/{id}")
    public String editEventPage(@PathVariable Integer id, Model model){
        ProductEvent productEvent = productEventService.getProductEventById(id);
        model.addAttribute("product", productService.getProductById(productEvent.getProduct().getId()));
        model.addAttribute("productEvent", productEvent);
        return "/events/edit";
    }
    @PostMapping("/delivery")
    public String saveEventDelivery(@ModelAttribute ProductEvent productEvent){
        productEvent.setType(ProductEventType.DELIVERY);
        productEvent.setLocationType(LocationType.WAREHOUSE);
        productEventService.saveProductEvent(productEvent);
        return "redirect:/warehouses/products/" + productEvent.getProduct().getId();
    }
    @PostMapping("/product-event/{carId}")
    public String handleProductEvent(@PathVariable Integer carId,@ModelAttribute ProductEvent productEvent) {

        switch (productEvent.getType().name()) {
            case "TRANSFER":
                productEvent.setLocationType(LocationType.CAR);
                productEvent.setCar(carService.getCarById(carId));
                productEvent.setTimestamp(LocalDateTime.now());
                productEventService.saveProductEvent(productEvent);
                break;
            case "RETURN":
                productEvent.setLocationType(LocationType.CAR);
                productEvent.setCar(carService.getCarById(carId));
                productEvent.setTimestamp(LocalDateTime.now());
                productEventService.saveProductEvent(productEvent);
                break;
            case "CONSUMPTION":
                Optional<Task> task = taskService.getTaskByCarIdAndDate(carId, LocalDate.now());
                if (task.isEmpty()) {
                    throw new IllegalStateException("Nie znaleziono aktywnego zadania dla samochodu o ID: " + carId);
                }
                productEvent.setLocationType(LocationType.JOB);
                productEvent.setCar(carService.getCarById(carId));
                productEvent.setTask(task.get());
                productEvent.setTimestamp(LocalDateTime.now());
                productEventService.saveProductEvent(productEvent);
                break;

            default:
                throw new IllegalArgumentException("Nieznany typ akcji: " + productEvent.getType().name());
        }

        return "redirect:/cars/info/" + carId;
    }
//    @PostMapping("/transfer")
//    public String saveEventTransfer(@ModelAttribute ProductEvent productEvent){
//        productEvent.setType(ProductEventType.TRANSFER);
//        productEvent.setLocationType(LocationType.CAR);
//        productEventService.saveProductEvent(productEvent);
//        return "redirect:/warehouses/products/" + productEvent.getProduct().getId();
//    }
//    @PostMapping("/consumption")
//    public String saveEventConsumption(@ModelAttribute ProductEvent productEvent){
//        productEvent.setType(ProductEventType.CONSUMPTION);
//        productEvent.setLocationType(LocationType.JOB);
//        productEventService.saveProductEvent(productEvent);
//        return "redirect:/warehouses/products/" + productEvent.getProduct().getId();
//    }
//    @PostMapping("/return")
//    public String saveEventReturn(@ModelAttribute ProductEvent productEvent){
//        productEvent.setType(ProductEventType.DELIVERY);
//        productEvent.setLocationType(LocationType.WAREHOUSE);
//        productEventService.saveProductEvent(productEvent);
//        return "redirect:/warehouses/products/" + productEvent.getProduct().getId();
//    }
}
