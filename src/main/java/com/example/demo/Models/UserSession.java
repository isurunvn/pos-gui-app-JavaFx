package com.example.demo.Models;

public class UserSession {
    private int userId;
    private String userName;
    private String role;

    public UserSession(int userId, String userName, String role) {
        this.userId = userId;
        this.userName = userName;
        this.role = role;
    }

    public int getUserID() {
        return userId;
    }

    public String getUserName() { return userName; }

    public String getRole() {
        return role;
    }
}
