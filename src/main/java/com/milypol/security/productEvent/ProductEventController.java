package com.milypol.security.productEvent;

import com.milypol.security.car.CarService;
import com.milypol.security.product.ProductService;
import com.milypol.security.task.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/events")
public class ProductEventController {
    private final ProductEventService productEventService;
    private final ProductService productService;
    private final TaskService taskService;
    private final CarService carService;

    public ProductEventController(ProductEventService productEventService,
                                  ProductService productService,
                                  TaskService taskService,
                                  CarService carService) {
        this.productEventService = productEventService;
        this.productService = productService;
        this.taskService = taskService;
        this.carService = carService;
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
        model.addAttribute("product", productService.getProductById(productId));
        model.addAttribute("productEvent", productEvent);
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("tasks", taskService.getAllTasks());
        return "/product/events-edit";
    }

    @GetMapping("/edit/{id}")
    public String editEventPage(@PathVariable Integer id, Model model){
        ProductEvent productEvent = productEventService.getProductEventById(id);
        model.addAttribute("product", productService.getProductById(productEvent.getProduct().getId()));
        model.addAttribute("productEvent", productEvent);
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("tasks", taskService.getAllTasks());
        return "/product/events-edit";
    }

    @PostMapping("/delivery/save")
    public String saveEventDelivery(@ModelAttribute ProductEvent productEvent){
        productEvent.setEventType(ProductEventType.DELIVERY);
        productEvent.setToLocationType(LocationType.WAREHOUSE);
        productEventService.saveProductEvent(productEvent);
        return "redirect:/warehouses/products/" + productEvent.getProduct().getId();
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
