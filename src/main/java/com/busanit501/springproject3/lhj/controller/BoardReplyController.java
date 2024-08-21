package com.busanit501.springproject3.lhj.controller;

import com.busanit501.springproject3.lhj.dto.BoardReplyDTO;
import com.busanit501.springproject3.lhj.service.BoardReplyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/board-replie")
public class BoardReplyController {

    private final BoardReplyService boardReplyService;

    @Autowired
    public BoardReplyController(BoardReplyService boardReplyService) {
        this.boardReplyService = boardReplyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardReplyDTO> getBoardReplyById(@PathVariable Long id) {
        BoardReplyDTO boardReply = boardReplyService.getBoardReplyById(id);
        return ResponseEntity.ok(boardReply);
    }

    @PostMapping
    public ResponseEntity<BoardReplyDTO> createBoardReply(@RequestBody BoardReplyDTO boardReplyDTO) {
        BoardReplyDTO newBoardReply = boardReplyService.createBoardReply(boardReplyDTO);
        return ResponseEntity.ok(newBoardReply);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardReplyDTO> updateBoardReply(@PathVariable Long id, @RequestBody BoardReplyDTO boardReplyDTO) {
        BoardReplyDTO updatedBoardReply = boardReplyService.updateBoardReply(id, boardReplyDTO);
        return ResponseEntity.ok(updatedBoardReply);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoardReply(@PathVariable Long id) {
        boardReplyService.deleteBoardReply(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<BoardReplyDTO>> getAllBoardReplies() {
        List<BoardReplyDTO> boardReplies = boardReplyService.getAllBoardReplies();
        return ResponseEntity.ok(boardReplies);
    }
}