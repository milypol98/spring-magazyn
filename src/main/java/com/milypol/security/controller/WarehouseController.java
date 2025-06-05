package com.milypol.security.controller;

import com.milypol.security.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    private final ProductService productService;

    public WarehouseController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showWarehousePage(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "warehouse";
    }

}
