package com.milypol.security.productEvent;

import com.milypol.security.car.CarService;
import com.milypol.security.product.ProductService;
import com.milypol.security.task.TaskService;
import com.milypol.security.user.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/events")
public class ProductEventController {
    private final ProductEventService productEventService;
    private final ProductService productService;
    private final TaskService taskService;
    private final CarService carService;
    private final UserService userService;

    public ProductEventController(ProductEventService productEventService,
                                  ProductService productService,
                                  TaskService taskService,
                                  CarService carService, UserService userService) {
        this.productEventService = productEventService;
        this.productService = productService;
        this.taskService = taskService;
        this.carService = carService;
        this.userService = userService;
    }

    @ModelAttribute("eventTypes")
    public ProductEventType[] eventTypes() {
        return ProductEventType.values();
    }

    @ModelAttribute("locationTypes")
    public LocationType[] locationTypes() {
        return LocationType.values();
    }

    @GetMapping("/add/{productId}")
    public String addEventPage(@PathVariable Integer productId, Model model){
        ProductEvent productEvent = new ProductEvent();
        productEvent.setProduct(productService.getProductById(productId));
        model.addAttribute("productEvent", productEvent);
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("tasks", taskService.getAllTasks());
        return "/product/events-edit";
    }


    @PostMapping("/save")
    public String saveEventDelivery(@ModelAttribute ProductEvent productEvent, @AuthenticationPrincipal UserDetails principal){
        productEvent.setUser(userService.getByEmail(principal.getUsername()));
        productEvent.setTimestamp(LocalDateTime.now());
        productEventService.saveProductEvent(productEvent);
        return "redirect:/product-positions/" + productEvent.getProduct().getId();
    }

    @PostMapping("/product-event/bulk/{carId}")
    public String saveBulkFromCar(@PathVariable Integer carId,
                                  @RequestParam Map<String, String> allParams,
                                  @RequestParam("type") String type,
                                  @RequestParam(value = "comment", required = false) String comment) {
        productEventService.saveBulkFromCarEvents(carId, allParams, type, comment);
        return "redirect:/cars/info/" + carId;
    }
}
