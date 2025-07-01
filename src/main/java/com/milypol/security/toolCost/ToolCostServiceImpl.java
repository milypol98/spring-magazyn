package com.milypol.security.toolCost;

import org.springframework.stereotype.Service;

@Service
public class ToolCostServiceImpl implements ToolCostService{
    private final ToolCostRepo toolCostRepo;

    public ToolCostServiceImpl(ToolCostRepo toolCostRepo) {
        this.toolCostRepo = toolCostRepo;
    }


    @Override
    public ToolCost getToolCostById(Integer id) {
        return toolCostRepo.findById(id).orElseThrow(() -> new RuntimeException("ProductCost not found"));
    }

    @Override
    public ToolCost saveToolCost(ToolCost toolCost) {
        return toolCostRepo.save(toolCost);
    }

    @Override
    public void deleteToolCost(Integer id) {
        toolCostRepo.deleteById(id);
    }
}
