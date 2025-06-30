package com.milypol.security.controller;

import com.milypol.security.cart.Cart;
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
//    @GetMapping("/products/{id}")
//    public String showProduct(@PathVariable Integer id, Model model) {
//        model.addAttribute("product", productService.getAllProductsByStockPositionId(id));
//        return "warehouses/productList";
//    }
    @GetMapping("/products/add/{positionId}")
    public String addProduct(@PathVariable Integer positionId, Model model) {
        Product product = new Product();
        product.setStockPosition(stockPositionService.getStockPositionById(positionId));
        model.addAttribute("product", product);
        return "warehouses/productEdit";
    }
    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("tasks", taskService.getAllTasksByProductId(id));
        return "warehouses/productEdit";
    }
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/warehouses/edit/" + product.getStockPosition().getId();
    }
    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/warehouses/products";
    }
    @GetMapping("/products/cost/add/{productId}")
    public String productCostAdd(@PathVariable Integer productId, Model model) {
        ProductCost productCost = new ProductCost();
        productCost.setProduct(productService.getProductById(productId));
        model.addAttribute("product_cost", productCost);
        return "warehouses/costEdit";
    }

    @GetMapping("/products/cost/edit/{id}")
    public String productCostEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("product_cost", productCostService.getProductCostById(id));
        return "warehouses/costEdit";
    }
    @PostMapping( "products/cost/save")
        public String saveCostProduct(@ModelAttribute ProductCost productCost) {
        productCostService.saveProductCost(productCost);
        return "redirect:/warehouses/products/edit/" + productCost.getProduct().getId();
    }
    @PostMapping("/products/cost/delete/{id}")
    public String deleteCostProduct(@PathVariable Integer id){
        productCostService.deleteProductCost(id);
        return "redirect:/productEdit";
    }
    @GetMapping( "/carts/add")
    public String addCart(Model model) {
        model.addAttribute("cart", new Cart());
        model.addAttribute("allStackPositions", stockPositionService.getAllStockPositions());
        return "warehouses/cartEdit";
    }
    @GetMapping( "/carts/edit/{id}")
    public String editCart(@PathVariable Integer id, Model model) {
        model.addAttribute("cart", cartService.getCartById(id));
        model.addAttribute("allStackPositions", stockPositionService.getAllStockPositions());
        return "warehouses/cartEdit";
    }
    @PostMapping( "/carts/save")
    public String saveCart(@ModelAttribute Cart cart) {
        cartService.saveCart(cart);
        return "redirect:/warehouses";
    }

    @PostMapping( "/carts/delete/{id}")
    public String deleteCart(@PathVariable Integer id){
        cartService.deleteCart(id);
        return "redirect:/warehouses";
    }
}
