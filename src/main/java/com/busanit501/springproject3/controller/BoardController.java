package com.busanit501.springproject3.controller;

import com.busanit501.springproject3.dto.BoardDto;
import com.busanit501.springproject3.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public String getAllBoards(Model model) {
        List<BoardDto> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "board-list";
    }

    @GetMapping("/{id}")
    public String getBoardById(@PathVariable Long id, Model model) {
        BoardDto board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "board-detail";
    }

    @GetMapping("/new")
    public String createBoardForm(Model model) {
        model.addAttribute("boardDto", new BoardDto());
        return "board-form";
    }

    @PostMapping
    public String createBoard(@ModelAttribute BoardDto boardDto) {
        boardService.createBoard(boardDto);
        return "redirect:/boards";
    }

    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/boards";
    }

}
