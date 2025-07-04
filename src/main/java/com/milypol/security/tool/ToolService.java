package com.milypol.security.tool;

import java.util.List;
import java.util.Map;

public interface ToolService {
    List<Tool> getAllTools();
    Tool getToolById(Integer id);
    Tool saveTool(Tool tool);
    void deleteTool(Integer id);
    List<Tool> getAllToolsByStockPositionId(Integer stockPositionId);
    Map<Integer, Long> getToolCountByStockPositionId();

    Map<Integer, Long> getToolCountByStockPositionIdStatus();

    Integer countByStatus(ToolStatus status);
}
