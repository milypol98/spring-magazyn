package com.milypol.security.tool;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ToolServiceImpl implements ToolService{

    private final ToolRepo toolRepo;

    public ToolServiceImpl(ToolRepo toolRepo) {
        this.toolRepo = toolRepo;
    }


    @Override
    public List<Tool> getAllTools() {
        return toolRepo.findAll();
    }

    @Override
    public Tool getToolById(Integer id) {
        return toolRepo.findById(id).orElseThrow(() -> new RuntimeException("Tool not found"));
    }

    @Override
    public Tool saveTool(Tool tool) {
        return toolRepo.save(tool);
    }

    @Override
    public void deleteTool(Integer id) {
        toolRepo.deleteById(id);
    }

    @Override
    public List<Tool> getAllToolsByStockPositionId(Integer stockPositionId) {
        return toolRepo.findAllByStockPosition_Id(stockPositionId);
    }

    @Override
    public Map<Integer, Long> getToolCountByStockPositionId() {
        return toolRepo.findAll().stream()
                .filter(t -> t.getStockPosition() != null)
                .collect(Collectors.groupingBy(
                        t -> t.getStockPosition().getId(),
                        Collectors.counting()
                ));
    }
    @Override
    public Map<Integer, Long> getToolCountByStockPositionIdStatus() {
        return toolRepo.findAll().stream()
                .filter(t -> t.getStockPosition() != null)
                .filter(t -> t.getStatus() == ToolStatus.STOCK)
                .collect(Collectors.groupingBy(
                        t -> t.getStockPosition().getId(),
                        Collectors.counting()
                ));
    }
    @Override
    public Integer countByStatus(ToolStatus status) {
        return toolRepo.countByStatus(status);
    }
}
