package com.busanit501.springproject3.lhj.controller;

import com.busanit501.springproject3.lhj.dto.BoardManagerDTO;
import com.busanit501.springproject3.lhj.service.BoardManagerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/board-manager")
public class BoardManagerController {

    private final BoardManagerService boardManagerService;

    @Autowired
    public BoardManagerController(BoardManagerService boardManagerService) {
        this.boardManagerService = boardManagerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardManagerDTO> getBoardManagerById(@PathVariable Long id) {
        BoardManagerDTO boardManager = boardManagerService.getBoardManagerById(id);
        return ResponseEntity.ok(boardManager);
    }

    @PostMapping
    public ResponseEntity<BoardManagerDTO> createBoardManager(@RequestBody BoardManagerDTO boardManagerDTO) {
        BoardManagerDTO newBoardManager = boardManagerService.createBoardManager(boardManagerDTO);
        return ResponseEntity.ok(newBoardManager);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardManagerDTO> updateBoardManager(@PathVariable Long id, @RequestBody BoardManagerDTO boardManagerDTO) {
        BoardManagerDTO updatedBoardManager = boardManagerService.updateBoardManager(id, boardManagerDTO);
        return ResponseEntity.ok(updatedBoardManager);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoardManager(@PathVariable Long id) {
        boardManagerService.deleteBoardManager(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BoardManagerDTO>> getAllBoardManagers() {
        List<BoardManagerDTO> boardManagers = boardManagerService.getAllBoardManagers();
        return ResponseEntity.ok(boardManagers);
    }
}