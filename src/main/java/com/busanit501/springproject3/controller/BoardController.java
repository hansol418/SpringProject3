package com.busanit501.springproject3.controller;

import com.busanit501.springproject3.dto.BoardDto;
import com.busanit501.springproject3.dto.CommentDto;
import com.busanit501.springproject3.entity.Comment;
import com.busanit501.springproject3.service.BoardService;
import com.busanit501.springproject3.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public String getAllBoards(Model model) {
        List<BoardDto> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "board-list";
    }

    @GetMapping("/{id}")
    public String getBoardById(@PathVariable("id") Long id, Model model) {
        BoardDto board = boardService.getBoardById(id); // Fetch board details

        // Fetch and set comments
        List<Comment> comments = commentService.getCommentsByBoardId(id);
        board.setAnswerList(comments);

        model.addAttribute("board", board); // Add board to model
        return "board-detail"; // View name for board details
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

    @GetMapping("/edit/{id}")
    public String editBoardForm(@PathVariable Long id, Model model) {
        BoardDto board = boardService.getBoardById(id);
        model.addAttribute("boardDto", board);
        return "board-form";
    }

    @PostMapping("/update")
    public String updateBoard(@ModelAttribute BoardDto boardDto, @RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes) {
        try {
            // Check if file is not empty
            if (!file.isEmpty()) {
                // Save or update the board with file
                boardService.saveOrUpdateItemWithFile(boardDto, file);
            } else {
                // Save or update the board without file
                boardService.saveOrUpdateItem(boardDto);
            }
            redirectAttributes.addFlashAttribute("message", "Board updated successfully!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Failed to upload file: " + e.getMessage());
        }
        return "redirect:/boards";
    }

    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            boardService.deleteBoard(id);
            redirectAttributes.addFlashAttribute("message", "Board deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete board: " + e.getMessage());
        }
        return "redirect:/boards";
    }

}
