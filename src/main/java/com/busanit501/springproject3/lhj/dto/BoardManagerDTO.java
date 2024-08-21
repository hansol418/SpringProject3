package com.busanit501.springproject3.lhj.dto;

public class BoardManagerDTO {
    private Long id;
    private String userId;
    private String mpass;
    private String mEmail;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
