package com.milypol.security.controller;

import com.milypol.security.product.ProductService;
import com.milypol.security.productEvent.LocationType;
import com.milypol.security.productEvent.ProductEvent;
import com.milypol.security.productEvent.ProductEventService;
import com.milypol.security.productEvent.ProductEventType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/events")
public class ProductEventController {
    private final ProductEventService productEventService;
    private final ProductService productService;

    public ProductEventController(ProductEventService productEventService, ProductService productService) {
        this.productEventService = productEventService;
        this.productService = productService;
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
    @PostMapping("/save")
    public String saveEvent(@ModelAttribute ProductEvent productEvent){
        productEvent.setType(ProductEventType.DELIVERY);
        productEvent.setLocationType(LocationType.WAREHOUSE);
        productEventService.saveProductEvent(productEvent);
        return "redirect:/warehouses/products/" + productEvent.getProduct().getId();
    }
}
