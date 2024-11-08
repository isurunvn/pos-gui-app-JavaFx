package com.example.demo.Models;

public class Admin {
    private int AdminID;
    private String AdminName;
    private String Password;

    public Admin(int AdminID, String AdminName, String Password) {
        this.AdminID = AdminID;
        this.AdminName = AdminName;
        this.Password = Password;
    }

    public int getAdminID() {
        return AdminID;
    }

    public void setAdminID(int AdminID) {
        this.AdminID = AdminID;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String AdminName) {
        this.AdminName = AdminName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
