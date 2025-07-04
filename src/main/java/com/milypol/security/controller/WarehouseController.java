package com.milypol.security.controller;

import com.milypol.security.cart.Cart;
import com.milypol.security.cart.CartService;
import com.milypol.security.product.Product;
import com.milypol.security.product.ProductService;
import com.milypol.security.stockPosition.TypePosition;
import com.milypol.security.task.TaskStatus;
import com.milypol.security.tool.ToolService;
import com.milypol.security.tool.ToolStatus;
import com.milypol.security.toolCost.ToolCost;
import com.milypol.security.toolCost.ToolCostService;
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
    private final CartService cartService;
    private final ToolCostService toolCostService;
    private final TaskService taskService;
    private final ToolService toolService;

    public WarehouseController(ProductService productService, StockPositionService stockPositionService, CartService cartService, ToolCostService toolCostService, TaskService taskService, ToolService toolService) {
        this.productService = productService;
        this.stockPositionService = stockPositionService;
        this.cartService = cartService;
        this.toolCostService = toolCostService;
        this.taskService = taskService;
        this.toolService = toolService;
    }

    @GetMapping
    public String showWarehousePage(Model model) {
        model.addAttribute("stockPositions", stockPositionService.getAllStockPositions());
        model.addAttribute("carts", cartService.getAllCarts());
        model.addAttribute("countToolsPosition", toolService.getToolCountByStockPositionId());
        model.addAttribute("typeProduct", TypePosition.PRODUCT);
        model.addAttribute("typeTool", TypePosition.TOOL);
        model.addAttribute("tasksUnpacked", taskService.getAllTasksByStatus(TaskStatus.TO_BE_UNPACKED));
        model.addAttribute("tasksPacked", taskService.getAllTasksByStatus(TaskStatus.TO_BE_PACKED));
        model.addAttribute("countToolsStatus",toolService.getToolCountByStockPositionIdStatus());
        return "warehouses/list";
    }
    @GetMapping("/stocks/tools")
    public String addStockPositionTool(Model model) {
        model.addAttribute("stockPosition", new StockPosition());
        return "warehouses/stockPositionToolEdit";
    }
    @GetMapping("/stocks/tools/{id}")
    public String editStockPositionTool(@PathVariable Integer id, Model model) {
        model.addAttribute("stockPosition", stockPositionService.getStockPositionById(id));
        model.addAttribute("toolStatusUse", ToolStatus.USE);
        model.addAttribute("toolStatusStock", ToolStatus.STOCK);
        model.addAttribute("toolStatusRepair", ToolStatus.REPAIR);
        return "warehouses/stockPositionToolEdit";
    }
    @PostMapping("/stocks/tools/save")
    public String saveStockPositionTool(@ModelAttribute StockPosition stockPosition) {
        stockPosition.setType(TypePosition.TOOL);
        stockPositionService.saveStockPosition(stockPosition);
        return "redirect:/warehouses";
    }
    @PostMapping("/stocks/tools/delete/{id}")
    public String deleteStockPositionTool(@PathVariable Integer id) {
        stockPositionService.deleteStockPosition(id);
        return "redirect:/warehouses";
    }



    @GetMapping("/stocks/products")
    public String addStockPositionProduct(Model model) {
        model.addAttribute("stockPosition", new StockPosition());
        return "warehouses/stockPositionProductEdit";
    }
    @GetMapping("/stocks/products/{id}")
    public String editStockPositionProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("stockPosition", stockPositionService.getStockPositionById(id));
        return "warehouses/stockPositionProductEdit";
    }
    @PostMapping("/stocks/products/save")
    public String saveStockPosition(@ModelAttribute StockPosition stockPosition) {
        stockPosition.setType(TypePosition.PRODUCT);
        stockPositionService.saveStockPosition(stockPosition);
        return "redirect:/warehouses";
    }
    @PostMapping("/stocks/products/delete/{id}")
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
    @GetMapping("/products/cost/add/{toolId}")
    public String productCostAdd(@PathVariable Integer toolId, Model model) {
        ToolCost toolCost = new ToolCost();
        toolCost.setCost(toolCostService.getToolCostById(toolId).getCost());
        model.addAttribute("product_cost", toolCost);
        return "warehouses/costEdit";
    }

    @GetMapping("/products/cost/edit/{id}")
    public String productCostEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("product_cost", toolCostService.getToolCostById(id));
        return "warehouses/costEdit";
    }
    @PostMapping( "products/cost/save")
        public String saveCostProduct(@ModelAttribute ToolCost toolCost) {
        toolCostService.saveToolCost(toolCost);
        return "redirect:/warehouses/products/edit/" + toolCost.getTool().getId();
    }
    @PostMapping("/products/cost/delete/{id}")
    public String deleteCostProduct(@PathVariable Integer id){
        toolCostService.deleteToolCost(id);
        return "redirect:/productEdit";
    }

}
