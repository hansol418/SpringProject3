package com.busanit501.springproject3.lhj.service;

import com.busanit501.springproject3.lhj.dto.BoardManagerDTO;

import java.util.List;

public interface BoardManagerService {
    BoardManagerDTO getBoardManagerById(Long id);
    BoardManagerDTO createBoardManager(BoardManagerDTO boardManagerDTO);
    BoardManagerDTO updateBoardManager(Long id, BoardManagerDTO boardManagerDTO);
    void deleteBoardManager(Long id);
    List<BoardManagerDTO> getAllBoardManagers();
}
