package com.busanit501.springproject3.lhj.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "board")
public class Board extends BaseEntity {

    @Column(name = "board_userid")
    private String boardUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    @Column(name = "writer")
    private String writer;

    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    // Getters and setters

    public String getBoardUserId() {
        return boardUserId;
    }

    public void setBoardUserId(String boardUserId) {
        this.boardUserId = boardUserId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}