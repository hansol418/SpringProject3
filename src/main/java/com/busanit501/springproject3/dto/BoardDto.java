package com.busanit501.springproject3.dto;

import com.busanit501.springproject3.entity.Board;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {

    private Long id;
    private String title;
    private String writer;
    private String boardContent;
    private String filename;
    private String filepath;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
 // Add this field

    // Convert DTO to Entity
    public Board toEntity() {
        Board board = new Board();
        board.setId(this.id);
        board.setTitle(this.title);
        board.setWriter(this.writer);
        board.setBoardContent(this.boardContent);
        board.setFilename(this.filename);
        board.setFilepath(this.filepath);
        board.setCreateDate(this.createDate);
        board.setModifyDate(this.modifyDate);
        // Assuming Board entity has a list of comments
        // board.setComments(this.comments.stream().map(CommentDto::toEntity).collect(Collectors.toList()));
        return board;
    }

    // Convert Entity to DTO
    public static BoardDto fromEntity(Board board) {
        BoardDto dto = new BoardDto();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setWriter(board.getWriter());
        dto.setBoardContent(board.getBoardContent());
        dto.setFilename(board.getFilename());
        dto.setFilepath(board.getFilepath());
        dto.setCreateDate(board.getCreateDate());
        dto.setModifyDate(board.getModifyDate());
        // Assuming Board entity has a list of comments
        // dto.setComments(board.getComments().stream().map(CommentDto::fromEntity).collect(Collectors.toList()));
        return dto;
    }
}
