package com.busanit501.springproject3.lhj.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "board_manager")
public class BoardManager extends BaseEntity {

    @Column(name = "userid", unique = true)
    private String userId;

    @Column(name = "mpass")
    private String mpass;

    @Column(name = "memail")
    private String mEmail;

    // Getters and setters
    public Long getId() {
        return super.getId(); // BaseEntity에서 상속된 getId() 메서드 호출
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMpass() {
        return mpass;
    }

    public void setMpass(String mpass) {
        this.mpass = mpass;
    }

    public String getMEmail() {
        return mEmail;
    }

    public void setMEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}