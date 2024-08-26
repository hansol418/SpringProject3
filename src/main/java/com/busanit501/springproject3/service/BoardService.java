package com.busanit501.springproject3.service;

import com.busanit501.springproject3.dto.BoardDto;
import com.busanit501.springproject3.entity.Board;
import com.busanit501.springproject3.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // Get all boards
    public List<BoardDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    // Get a board by ID
    public BoardDto getBoardById(Long id) {
        return boardRepository.findById(id)
                .map(this::convertEntityToDto)
                .orElseThrow(() -> new RuntimeException("Board not found"));
    }

    // Create a new board
    public BoardDto createBoard(BoardDto boardDto) {
        Board board = convertDtoToEntity(boardDto);
        board.setCreateDate(LocalDateTime.now());
        Board savedBoard = boardRepository.save(board);
        return convertEntityToDto(savedBoard);
    }

    // Delete a board by ID
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    // Save or update a board
    public BoardDto saveOrUpdateItem(BoardDto boardDto) {
        Board board;

        if (boardDto.getId() != null) {
            Optional<Board> boardOpt = boardRepository.findById(boardDto.getId());
            if (boardOpt.isPresent()) {
                board = boardOpt.get();
                board.setModifyDate(LocalDateTime.now()); // Update modifyDate if the board exists

                // Update other fields
                board.setTitle(boardDto.getTitle());
                board.setWriter(boardDto.getWriter());
                board.setBoardContent(boardDto.getBoardContent());
                board.setFilename(boardDto.getFilename());
                board.setFilepath(boardDto.getFilepath());

                // Keep existing createDate
                board.setCreateDate(board.getCreateDate());
            } else {
                throw new RuntimeException("Item not found for update");
            }
        } else {
            board = convertDtoToEntity(boardDto);
            board.setCreateDate(LocalDateTime.now()); // Set createDate for new board
        }

        Board savedItem = boardRepository.save(board);
        return convertEntityToDto(savedItem);
    }

    // Save or update a board with a file
    public BoardDto saveOrUpdateItemWithFile(BoardDto boardDto, MultipartFile file) throws IOException {
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);

        Board board;
        if (boardDto.getId() != null) {
            Optional<Board> boardOpt = boardRepository.findById(boardDto.getId());
            if (boardOpt.isPresent()) {
                board = boardOpt.get();
                board.setModifyDate(LocalDateTime.now()); // Update modifyDate if the board exists

                // Update other fields
                board.setTitle(boardDto.getTitle());
                board.setWriter(boardDto.getWriter());
                board.setBoardContent(boardDto.getBoardContent());
                board.setFilename(fileName);
                board.setFilepath("/files/" + fileName);

                // Keep existing createDate
                board.setCreateDate(board.getCreateDate());
            } else {
                throw new RuntimeException("Item not found for update");
            }
        } else {
            board = convertDtoToEntity(boardDto);
            board.setCreateDate(LocalDateTime.now()); // Set createDate for new board
            board.setFilename(fileName);
            board.setFilepath("/files/" + fileName);
        }

        Board savedItem = boardRepository.save(board);
        return convertEntityToDto(savedItem);
    }

    private BoardDto convertEntityToDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setWriter(board.getWriter());
        boardDto.setBoardContent(board.getBoardContent());
        boardDto.setFilename(board.getFilename());
        boardDto.setFilepath(board.getFilepath());
        boardDto.setCreateDate(board.getCreateDate());
        boardDto.setModifyDate(board.getModifyDate());
        return boardDto;
    }

    private Board convertDtoToEntity(BoardDto boardDto) {
        Board board = new Board();
        board.setId(boardDto.getId());
        board.setTitle(boardDto.getTitle());
        board.setWriter(boardDto.getWriter());
        board.setBoardContent(boardDto.getBoardContent());
        board.setFilename(boardDto.getFilename());
        board.setFilepath(boardDto.getFilepath());
        board.setCreateDate(boardDto.getCreateDate());
        board.setModifyDate(boardDto.getModifyDate());
        return board;
    }
}
