package com.busanit501.springproject3.msy.dto;
//
import com.busanit501.springproject3.msy.entity.Board;
import com.busanit501.springproject3.msy.entity.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<Comment> answerList;
    // New field for view count
    private int viewCount;


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
        board.setViewCount(this.viewCount);
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
        dto.setAnswerList(board.getComments());
        dto.setViewCount(board.getViewCount());
        return dto;
    }
}
