package com.milypol.security.tool;

import com.milypol.security.stockPosition.StockPositionService;
import com.milypol.security.toolCost.ToolCost;
import com.milypol.security.toolCost.ToolCostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tools")
public class ToolController {
    private final ToolService toolService;
    private final ToolCostService toolCostService;
    private final StockPositionService stockPositionService;

    public ToolController(ToolService toolService, ToolCostService toolCostService, StockPositionService stockPositionService) {
        this.toolService = toolService;
        this.toolCostService = toolCostService;
        this.stockPositionService = stockPositionService;
    }
    @GetMapping("/{id}")
    public String infoTool(@PathVariable Integer id, Model model) {
        model.addAttribute("tool", toolService.getToolById(id));
        return "tools/info";
    }
    @GetMapping("/add/{stockId}")
    public String addTool(@PathVariable Integer stockId, Model model) {
        Tool tool = new Tool();
        tool.setStockPosition(stockPositionService.getStockPositionById(stockId));
        model.addAttribute("tool", tool);
        return "tools/edit";
    }
    @GetMapping("/edit/{id}")
    public String editTool(@PathVariable Integer id, Model model) {
        model.addAttribute("tool", toolService.getToolById(id));
        return "tools/edit";
    }
    @PostMapping("/save")
    public String saveTool(@ModelAttribute Tool tool) {
        toolService.saveTool(tool);
        return "redirect:/warehouses/stock-positions/" + tool.getStockPosition().getId();
    }
    @PostMapping("/delete/{id}")
    public String saveTool(@PathVariable Integer id) {
        toolService.deleteTool(id);
        return "redirect:/warehouses/stock-positions/" + toolService.getToolById(id).getStockPosition().getId();
    }


    @GetMapping("/cost/add/{toolId}")
    public String productCostAdd(@PathVariable Integer toolId, Model model) {
        ToolCost toolCost = new ToolCost();
        toolCost.setCost(toolCostService.getToolCostById(toolId).getCost());
        model.addAttribute("tool_cost", toolCost);
        model.addAttribute("tool", toolService.getToolById(toolId));
        return "warehouses/costEdit";
    }

    @GetMapping("/cost/edit/{id}")
    public String productCostEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("tool_cost", toolCostService.getToolCostById(id));
        model.addAttribute("tool", toolService.getToolById(id));
        return "warehouses/costEdit";
    }
    @PostMapping( "/cost/save")
    public String saveCostProduct(@ModelAttribute ToolCost toolCost) {
        toolCostService.saveToolCost(toolCost);
        return "redirect:/warehouses/products/edit/" + toolCost.getTool().getId();
    }
    @PostMapping("/cost/delete/{id}")
    public String deleteCostProduct(@PathVariable Integer id){
        toolCostService.deleteToolCost(id);
        return "redirect:/productEdit";
    }
}
