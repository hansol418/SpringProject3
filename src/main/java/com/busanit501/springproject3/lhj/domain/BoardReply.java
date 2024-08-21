package com.busanit501.springproject3.lhj.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "boardreply")
public class BoardReply extends BaseEntity {

    @Column(name = "userid")
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postid")
    private Board board;

    @Column(name = "reply")
    private String reply;

    @Column(name = "parentreplyid")
    private Long parentReplyId;

    // Getters and setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Long getParentReplyId() {
        return parentReplyId;
    }

    public void setParentReplyId(Long parentReplyId) {
        this.parentReplyId = parentReplyId;
    }
}
