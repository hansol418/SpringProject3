package com.busanit501.springproject3.lhj.service;

import com.busanit501.springproject3.lhj.domain.Board;
import com.busanit501.springproject3.lhj.domain.User;
import com.busanit501.springproject3.lhj.dto.BoardDTO;
import com.busanit501.springproject3.lhj.repository.BoardRepository;
import com.busanit501.springproject3.lhj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BoardDTO getBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        return convertToDTO(board);
    }

    @Override
    public BoardDTO createBoard(BoardDTO boardDTO) {
        Board board = convertToEntity(boardDTO);
        Board savedBoard = boardRepository.save(board);
        return convertToDTO(savedBoard);
    }

    @Override
    public BoardDTO updateBoard(Long id, BoardDTO boardDTO) {
        Board existingBoard = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        existingBoard.setBoardUserId(boardDTO.getBoardUserId());
        existingBoard.setWriter(boardDTO.getWriter());
        existingBoard.setContent(boardDTO.getContent());
        existingBoard.setTitle(boardDTO.getTitle());

        // 사용자 정보도 업데이트하는 경우
        if (boardDTO.getUserId() != null) {
            User user = userRepository.findById(boardDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existingBoard.setUser(user);
        }

        Board updatedBoard = boardRepository.save(existingBoard);
        return convertToDTO(updatedBoard);
    }

    @Override
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        boardRepository.delete(board);
    }

    @Override
    public List<BoardDTO> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BoardDTO convertToDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setBoardUserId(board.getBoardUserId());
        boardDTO.setWriter(board.getWriter());
        boardDTO.setContent(board.getContent());
        boardDTO.setTitle(board.getTitle());
        boardDTO.setUserId(board.getUser().getId()); // User ID 설정
        return boardDTO;
    }

    private Board convertToEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.setBoardUserId(boardDTO.getBoardUserId());
        board.setWriter(boardDTO.getWriter());
        board.setContent(boardDTO.getContent());
        board.setTitle(boardDTO.getTitle());

        // User 설정
        if (boardDTO.getUserId() != null) {
            User user = userRepository.findById(boardDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            board.setUser(user);
        }

        return board;
    }
}
