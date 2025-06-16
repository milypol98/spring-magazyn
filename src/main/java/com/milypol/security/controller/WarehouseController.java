package com.milypol.security.controller;

import com.milypol.security.cart.CartServiceImpl;
import com.milypol.security.product.Product;
import com.milypol.security.product.ProductService;
import com.milypol.security.productCost.ProductCost;
import com.milypol.security.productCost.ProductCostService;
import com.milypol.security.stockPosition.StockPosition;
import com.milypol.security.stockPosition.StockPositionService;
import com.milypol.security.task.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/warehouses")
public class WarehouseController {

    private final ProductService productService;
    private final StockPositionService stockPositionService;
    private final CartServiceImpl cartService;
    private final ProductCostService productCostService;
    private final TaskService taskService;

    public WarehouseController(ProductService productService, StockPositionService stockPositionService, CartServiceImpl cartService, ProductCostService productCostService, TaskService taskService) {
        this.productService = productService;
        this.stockPositionService = stockPositionService;
        this.cartService = cartService;
        this.productCostService = productCostService;
        this.taskService = taskService;
    }

    @GetMapping
    public String showWarehousePage(Model model) {
        model.addAttribute("stockPositions", stockPositionService.getAllStockPositions());
        model.addAttribute("carts", cartService.getAllCarts());
        return "warehouses/list";
    }
    @GetMapping("/add")
    public String addStockPosition(Model model) {
        model.addAttribute("stockPosition", new StockPosition());
        return "warehouses/edit";
    }
    @GetMapping("/edit/{id}")
    public String editStockPosition(@PathVariable Integer id, Model model) {
        model.addAttribute("stockPosition", stockPositionService.getStockPositionById(id));
        return "warehouses/edit";
    }
    @PostMapping("/save")
    public String saveStockPosition(@ModelAttribute StockPosition stockPosition) {
        stockPositionService.saveStockPosition(stockPosition);
        return "redirect:/warehouses";
    }
    @PostMapping("/delete/{id}")
    public String deleteStockPosition(@PathVariable Integer id) {
        stockPositionService.deleteStockPosition(id);
        return "redirect:/warehouses";
    }
    //produkty
    @GetMapping("/products/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getAllProductsByStockPositionId(id));
        return "warehouses/productList";
    }
    @GetMapping("/products/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "warehouses/productEdit";
    }
    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("productCost", new ProductCost());
        model.addAttribute("tasks", taskService.getAllTasksByProductId(id));
        return "warehouses/productEdit";
    }
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/warehouses/products";
    }
    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/warehouses/products";
    }

    @GetMapping("/products/cost/edit/{id}")
    public String productCostEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("productCost", productCostService.getProductCostById(id));
        return "warehouses/costEdit";
    }
    @PostMapping( "products/cost/save")
        public String saveCostProduct(@ModelAttribute ProductCost productCost) {
        productCostService.saveProductCost(productCost);
        return "redirect:/productEdit";
    }
    @PostMapping("/products/cost/delete/{id}")
    public String deleteCostProduct(@PathVariable Integer id){
        productCostService.deleteProductCost(id);
        return "redirect:/productEdit";
    }
    //todo koszyk brakuje

}
