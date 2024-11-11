package com.example.demo.Models;

public class Cashier {
    private int CashierID;
    private String CashierName;
    private String Password;
    private int ShopID;
    private String ShopName;

//    public Cashier(int CashierID, String CashierName, String Password, int ShopID) {
//        this.CashierID = CashierID;
//        this.CashierName = CashierName;
//        this.Password = Password;
//        this.ShopID = ShopID;
//    }

    public int getCashierID() {
        return CashierID;
    }
    public void setCashierID(int CashierID) {
        this.CashierID = CashierID;
    }

    public String getCashierName() {
        return CashierName;
    }
    public void setCashierName(String CashierName) {
        this.CashierName = CashierName;
    }

    public String getPassword() {
        return Password;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getShopID() {
        return ShopID;
    }
    public void setShopID(int ShopID) {
        this.ShopID = ShopID;
    }

    public String getShopName() {return ShopName;}
    public  void setShopName(String ShopName) {this.ShopName = ShopName;}

}
