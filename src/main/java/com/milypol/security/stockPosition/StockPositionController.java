package com.milypol.security.stockPosition;

import com.milypol.security.tool.ToolService;
import com.milypol.security.tool.ToolStatus;
import com.milypol.security.warehouse.Warehouse;
import com.milypol.security.warehouse.WarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stock-positions")
public class StockPositionController {
    private final StockPositionService stockPositionService;
    private final WarehouseService warehouseService;
    private final ToolService toolService;

    public StockPositionController(StockPositionService stockPositionService, WarehouseService warehouseService, ToolService toolService) {
        this.stockPositionService = stockPositionService;
        this.warehouseService = warehouseService;
        this.toolService = toolService;
    }
    @GetMapping("{id}")
    public String infoStockPosition(@PathVariable Integer id, Model model) {
        model.addAttribute("stockPosition", stockPositionService.getStockPositionById(id));
        model.addAttribute("tools", toolService.getAllToolsByStockPositionId(id));
        model.addAttribute("toolStatusUse", ToolStatus.USE);
        model.addAttribute("toolStatusStock", ToolStatus.STOCK);
        model.addAttribute("toolStatusRepair", ToolStatus.REPAIR);
        model.addAttribute("toolStatusInactive", ToolStatus.INACTIVE);
        model.addAttribute("toolStatusDeleted", ToolStatus.DELETED);
        return "stockPositions/info";
    }
    @GetMapping("/add/{warehouseId}")
    public String addStockPosition(@PathVariable Integer warehouseId, Model model) {
        Warehouse warehouse = warehouseService.findById(warehouseId)
                .orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe ID magazynu: " + warehouseId));
        StockPosition stockPosition = new StockPosition();
        stockPosition.setWarehouse(warehouse);
        model.addAttribute("stockPosition", stockPosition);
        return "stockPositions/edit";
    }
    @GetMapping("/edit/{id}")
    public String editStockPositionTool(@PathVariable Integer id, Model model) {
        model.addAttribute("stockPosition", stockPositionService.getStockPositionById(id));
        model.addAttribute("toolStatusUse", ToolStatus.USE);
        model.addAttribute("toolStatusStock", ToolStatus.STOCK);
        model.addAttribute("toolStatusRepair", ToolStatus.REPAIR);
        return "stockPositions/edit";
    }
    @PostMapping("/save")
    public String saveStockPositionTool(@ModelAttribute StockPosition stockPosition) {
        stockPositionService.saveStockPosition(stockPosition);
        return "redirect:/warehouses/" + stockPosition.getWarehouse().getId();
    }
    @PostMapping("/delete/{id}")
    public String deleteStockPositionTool(@PathVariable Integer id) {
        stockPositionService.deleteStockPosition(id);
        return "redirect:/warehouses/" + stockPositionService.getStockPositionById(id).getWarehouse().getId();
    }
}
