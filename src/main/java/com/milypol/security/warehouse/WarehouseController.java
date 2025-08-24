package com.milypol.security.warehouse;

import com.milypol.security.cart.CartService;
import com.milypol.security.product.Product;
import com.milypol.security.product.ProductService;
import com.milypol.security.productEvent.ProductEventService;
import com.milypol.security.productEvent.ProductEventType;
import com.milypol.security.task.TaskStatus;
import com.milypol.security.tool.Tool;
import com.milypol.security.tool.ToolService;
import com.milypol.security.tool.ToolStatus;
import com.milypol.security.toolCost.ToolCost;
import com.milypol.security.toolCost.ToolCostService;
import com.milypol.security.stockPosition.StockPosition;
import com.milypol.security.stockPosition.StockPositionService;
import com.milypol.security.task.TaskService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@PreAuthorize("hasAuthority('ADMIN')")
@Controller
@RequestMapping("/warehouses")
public class WarehouseController {

    private final ProductService productService;
    private final StockPositionService stockPositionService;
    private final CartService cartService;
    private final ToolCostService toolCostService;
    private final TaskService taskService;
    private final ToolService toolService;
    private final ProductEventService productEventService;

    public WarehouseController(ProductService productService, StockPositionService stockPositionService, CartService cartService, ToolCostService toolCostService, TaskService taskService, ToolService toolService, ProductEventService productEventService) {
        this.productService = productService;
        this.stockPositionService = stockPositionService;
        this.cartService = cartService;
        this.toolCostService = toolCostService;
        this.taskService = taskService;
        this.toolService = toolService;
        this.productEventService = productEventService;
    }

    @GetMapping( "/{warehouseId}")
    public String showWarehousePage(@PathVariable Integer warehouseId,Model model) {
        model.addAttribute("stockPositions", stockPositionService.getAllStockPositionsByWarehouseId(warehouseId));
        model.addAttribute("products", productService.getAllProductsByWarehouseId(warehouseId));
        model.addAttribute("countProductWarehouse",productEventService.getProductCountInWarehouse());
        model.addAttribute("countProductWarehouseAndCar",productEventService.getProductCountInWarehouseAndCar());
        model.addAttribute("carts", cartService.getAllCarts());
        model.addAttribute("countToolsStatus",toolService.getToolCountByStockPositionIdStatus());
        model.addAttribute("countToolsPosition", toolService.getToolCountByStockPositionId());
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
        stockPositionService.saveStockPosition(stockPosition);
        return "redirect:/warehouses";
    }
    @PostMapping("/stocks/tools/delete/{id}")
    public String deleteStockPositionTool(@PathVariable Integer id) {
        stockPositionService.deleteStockPosition(id);
        return "redirect:/warehouses";
    }
    @GetMapping("/tools/add/{stockId}")
    public String addTool(@PathVariable Integer stockId, Model model) {
        Tool tool = new Tool();
        tool.setStockPosition(stockPositionService.getStockPositionById(stockId));
        model.addAttribute("tool", tool);
        return "warehouses/toolEdit";
    }
    @GetMapping("/tools/edit/{id}")
    public String editTool(@PathVariable Integer id, Model model) {
        model.addAttribute("tool", toolService.getToolById(id));
        return "warehouses/toolEdit";
    }
    @PostMapping("/tools/save")
    public String saveTool(@ModelAttribute Tool tool) {
        toolService.saveTool(tool);
        return "redirect:/warehouses/stocks/tools/" + tool.getStockPosition().getId();
    }


    @GetMapping("/tools/cost/add/{toolId}")
    public String productCostAdd(@PathVariable Integer toolId, Model model) {
        ToolCost toolCost = new ToolCost();
        toolCost.setCost(toolCostService.getToolCostById(toolId).getCost());
        model.addAttribute("tool_cost", toolCost);
        model.addAttribute("tool", toolService.getToolById(toolId));
        return "warehouses/costEdit";
    }

    @GetMapping("/tools/cost/edit/{id}")
    public String productCostEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("tool_cost", toolCostService.getToolCostById(id));
        model.addAttribute("tool", toolService.getToolById(id));
        return "warehouses/costEdit";
    }
    @PostMapping( "tools/cost/save")
    public String saveCostProduct(@ModelAttribute ToolCost toolCost) {
        toolCostService.saveToolCost(toolCost);
        return "redirect:/warehouses/products/edit/" + toolCost.getTool().getId();
    }
    @PostMapping("/tools/cost/delete/{id}")
    public String deleteCostProduct(@PathVariable Integer id){
        toolCostService.deleteToolCost(id);
        return "redirect:/productEdit";
    }


    @GetMapping("/products/{id}")
    public String showProduct(@PathVariable Integer id,Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("productEventTypeDelivery", ProductEventType.DELIVERY);
        model.addAttribute("productEventTypeReturn", ProductEventType.RETURN);
        model.addAttribute("productEventTypeTransfer", ProductEventType.TRANSFER);
        model.addAttribute("productEventTypeUsed", ProductEventType.USED);
        return "warehouses/productInfo";
    }
    @GetMapping("/products/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "warehouses/productEdit";
    }
    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "warehouses/productEdit";
    }
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/warehouses/products/" + product.getId();
    }
    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/warehouses";
    }

}
