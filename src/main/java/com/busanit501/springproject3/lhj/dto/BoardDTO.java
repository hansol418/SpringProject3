package com.busanit501.springproject3.lhj.dto;

public class BoardDTO {
    private Long id;
    private String boardUserId;
    private String writer;
    private String content;
    private String title;
    private Long userId;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoardUserId() {
        return boardUserId;
    }

    public void setBoardUserId(String boardUserId) {
        this.boardUserId = boardUserId;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
