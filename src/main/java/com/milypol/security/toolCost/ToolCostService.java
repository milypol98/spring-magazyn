package com.milypol.security.toolCost;

public interface ToolCostService {
    ToolCost getToolCostById(Integer id);
    ToolCost saveToolCost(ToolCost toolCost);
    void deleteToolCost(Integer id);
}
