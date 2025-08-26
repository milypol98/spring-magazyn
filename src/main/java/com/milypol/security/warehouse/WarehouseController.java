package com.milypol.security.warehouse;

import com.milypol.security.cart.CartService;
import com.milypol.security.product.ProductPosition;
import com.milypol.security.product.ProductService;
import com.milypol.security.productEvent.ProductEventService;
import com.milypol.security.productEvent.ProductEventType;
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
    private final ToolService toolService;
    private final ProductEventService productEventService;

    public WarehouseController(ProductService productService, StockPositionService stockPositionService, ToolService toolService, ProductEventService productEventService) {
        this.productService = productService;
        this.stockPositionService = stockPositionService;
        this.toolService = toolService;
        this.productEventService = productEventService;
    }

    @GetMapping( "/{warehouseId}")
    public String showWarehousePage(@PathVariable Integer warehouseId,Model model) {
        model.addAttribute("stockPositions", stockPositionService.getAllStockPositionsByWarehouseId(warehouseId));
        model.addAttribute("products", productService.getAllProductsByWarehouseId(warehouseId));
        model.addAttribute("warehouseId", warehouseId);
        model.addAttribute("countProductWarehouse",productEventService.getProductCountInWarehouse());
        model.addAttribute("countProductWarehouseAndCar",productEventService.getProductCountInWarehouseAndCar());
        model.addAttribute("countToolsStatus",toolService.getToolCountByStockPositionIdStatus());
        model.addAttribute("countToolsPosition", toolService.getToolCountByStockPositionId());
        return "warehouses/list";
    }
}
