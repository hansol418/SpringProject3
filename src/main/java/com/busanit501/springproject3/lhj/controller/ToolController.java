package com.busanit501.springproject3.lhj.controller;

import com.busanit501.springproject3.lhj.dto.ToolDTO;
import com.busanit501.springproject3.lhj.exception.ToolNotFoundException;
import com.busanit501.springproject3.lhj.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tools")
public class ToolController {

    private final ToolService toolService;

    @Autowired
    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToolDTO> getToolById(@PathVariable String id) {
        ToolDTO tool = toolService.getToolById(id);
        return ResponseEntity.ok(tool);
    }

    @PostMapping
    public ResponseEntity<ToolDTO> createTool(@RequestBody ToolDTO toolDTO) {
        ToolDTO newTool = toolService.createTool(toolDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newTool.getId())
                .toUri();
        return ResponseEntity.created(location).body(newTool);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToolDTO> updateTool(@PathVariable String id, @RequestBody ToolDTO toolDTO) {
        ToolDTO updatedTool = toolService.updateTool(id, toolDTO);
        return ResponseEntity.ok(updatedTool);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTool(@PathVariable String id) {
        toolService.deleteTool(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ToolDTO>> getAllTools() {
        List<ToolDTO> tools = toolService.getAllTools();
        return ResponseEntity.ok(tools);
    }

    // 예외 처리
    @ExceptionHandler(ToolNotFoundException.class)
    public ResponseEntity<String> handleToolNotFoundException(ToolNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
