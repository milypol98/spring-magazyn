package com.milypol.security.tool;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolRepo extends JpaRepository<Tool, Integer> {
    List<Tool> findAllByStockPosition_Id(Integer stockPositionId);
    Integer countByStockPosition_Id(Integer stockPositionId);
}
