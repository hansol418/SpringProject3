package com.busanit501.springproject3.lhj.service;


import com.busanit501.springproject3.lhj.domain.BoardReply;
import com.busanit501.springproject3.lhj.dto.BoardReplyDTO;
import com.busanit501.springproject3.lhj.repository.BoardReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardReplyServiceImpl implements BoardReplyService {

    private final BoardReplyRepository boardReplyRepository;

    @Autowired
    public BoardReplyServiceImpl(BoardReplyRepository boardReplyRepository) {
        this.boardReplyRepository = boardReplyRepository;
    }

    @Override
    public BoardReplyDTO getBoardReplyById(Long id) {
        BoardReply boardReply = boardReplyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BoardReply not found"));
        return convertToDTO(boardReply);
    }

    @Override
    public BoardReplyDTO createBoardReply(BoardReplyDTO boardReplyDTO) {
        BoardReply boardReply = convertToEntity(boardReplyDTO);
        BoardReply savedBoardReply = boardReplyRepository.save(boardReply);
        return convertToDTO(savedBoardReply);
    }

    @Override
    public BoardReplyDTO updateBoardReply(Long id, BoardReplyDTO boardReplyDTO) {
        BoardReply existingBoardReply = boardReplyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BoardReply not found"));
        existingBoardReply.setReply(boardReplyDTO.getReply());
        existingBoardReply.setParentReplyId(boardReplyDTO.getParentReplyId());
        BoardReply updatedBoardReply = boardReplyRepository.save(existingBoardReply);
        return convertToDTO(updatedBoardReply);
    }

    @Override
    public void deleteBoardReply(Long id) {
        BoardReply boardReply = boardReplyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BoardReply not found"));
        boardReplyRepository.delete(boardReply);
    }

    @Override
    public List<BoardReplyDTO> getAllBoardReplies() {
        return boardReplyRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BoardReplyDTO convertToDTO(BoardReply boardReply) {
        BoardReplyDTO boardReplyDTO = new BoardReplyDTO();
        boardReplyDTO.setId(boardReply.getId());
        boardReplyDTO.setUserId(boardReply.getUserId());
        boardReplyDTO.setBoardId(boardReply.getBoard().getId());
        boardReplyDTO.setReply(boardReply.getReply());
        boardReplyDTO.setParentReplyId(boardReply.getParentReplyId());
        return boardReplyDTO;
    }

    private BoardReply convertToEntity(BoardReplyDTO boardReplyDTO) {
        BoardReply boardReply = new BoardReply();
        boardReply.setUserId(boardReplyDTO.getUserId());
        boardReply.setReply(boardReplyDTO.getReply());
        boardReply.setParentReplyId(boardReplyDTO.getParentReplyId());
        return boardReply;
    }
}
