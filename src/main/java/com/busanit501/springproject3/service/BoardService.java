package com.busanit501.springproject3.service;

import com.busanit501.springproject3.dto.BoardDto;
import com.busanit501.springproject3.entity.Board;
import com.busanit501.springproject3.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<BoardDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public BoardDto getBoardById(Long id) {
        return boardRepository.findById(id)
                .map(this::convertEntityToDto)
                .orElseThrow(() -> new RuntimeException("Board not found"));
    }

    public BoardDto createBoard(BoardDto boardDto) {
        Board board = convertDtoToEntity(boardDto);
        Board savedBoard = boardRepository.save(board);
        return convertEntityToDto(savedBoard);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    private BoardDto convertEntityToDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setWriter(board.getWriter());
        boardDto.setBoardContent(board.getBoardContent());
        return boardDto;
    }

    private Board convertDtoToEntity(BoardDto boardDto) {
        Board board = new Board();
        board.setId(boardDto.getId());
        board.setTitle(boardDto.getTitle());
        board.setWriter(boardDto.getWriter());
        board.setBoardContent(boardDto.getBoardContent());
        return board;
    }
}
