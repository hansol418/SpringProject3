package com.busanit501.springproject3.lhj.service;

import com.busanit501.springproject3.lhj.dto.ToolDTO;

import java.util.List;

public interface ToolService {
    ToolDTO getToolById(String id);
    ToolDTO createTool(ToolDTO toolDTO);
    ToolDTO updateTool(String id, ToolDTO toolDTO);
    void deleteTool(String id);
    List<ToolDTO> getAllTools();
}