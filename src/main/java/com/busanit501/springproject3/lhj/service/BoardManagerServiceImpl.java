package com.busanit501.springproject3.lhj.service;


import com.busanit501.springproject3.lhj.domain.BoardManager;
import com.busanit501.springproject3.lhj.dto.BoardManagerDTO;
import com.busanit501.springproject3.lhj.repository.BoardManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardManagerServiceImpl implements BoardManagerService {

    private final BoardManagerRepository boardManagerRepository;

    @Autowired
    public BoardManagerServiceImpl(BoardManagerRepository boardManagerRepository) {
        this.boardManagerRepository = boardManagerRepository;
    }

    @Override
    public BoardManagerDTO getBoardManagerById(Long id) {
        BoardManager boardManager = boardManagerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BoardManager not found"));
        return convertToDTO(boardManager);
    }

    @Override
    public BoardManagerDTO createBoardManager(BoardManagerDTO boardManagerDTO) {
        BoardManager boardManager = convertToEntity(boardManagerDTO);
        BoardManager savedBoardManager = boardManagerRepository.save(boardManager);
        return convertToDTO(savedBoardManager);
    }

    @Override
    public BoardManagerDTO updateBoardManager(Long id, BoardManagerDTO boardManagerDTO) {
        BoardManager existingBoardManager = boardManagerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BoardManager not found"));
        existingBoardManager.setUserId(boardManagerDTO.getUserId());
        existingBoardManager.setMpass(boardManagerDTO.getMpass());
        existingBoardManager.setMEmail(boardManagerDTO.getMEmail());
        BoardManager updatedBoardManager = boardManagerRepository.save(existingBoardManager);
        return convertToDTO(updatedBoardManager);
    }

    @Override
    public void deleteBoardManager(Long id) {
        BoardManager boardManager = boardManagerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BoardManager not found"));
        boardManagerRepository.delete(boardManager);
    }

    @Override
    public List<BoardManagerDTO> getAllBoardManagers() {
        return boardManagerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BoardManagerDTO convertToDTO(BoardManager boardManager) {
        BoardManagerDTO boardManagerDTO = new BoardManagerDTO();
        boardManagerDTO.setId(boardManager.getId());
        boardManagerDTO.setUserId(boardManager.getUserId());
        boardManagerDTO.setMpass(boardManager.getMpass());
        boardManagerDTO.setMEmail(boardManager.getMEmail());
        return boardManagerDTO;
    }

    private BoardManager convertToEntity(BoardManagerDTO boardManagerDTO) {
        BoardManager boardManager = new BoardManager();
        boardManager.setUserId(boardManagerDTO.getUserId());
        boardManager.setMpass(boardManagerDTO.getMpass());
        boardManager.setMEmail(boardManagerDTO.getMEmail());
        return boardManager;
    }
}
