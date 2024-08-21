package com.busanit501.springproject3.lhj.service;

import com.busanit501.springproject3.lhj.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    BoardDTO getBoardById(Long id);
    BoardDTO createBoard(BoardDTO boardDTO);
    BoardDTO updateBoard(Long id, BoardDTO boardDTO);
    void deleteBoard(Long id);
    List<BoardDTO> getAllBoards();
}
