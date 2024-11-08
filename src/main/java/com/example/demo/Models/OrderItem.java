package com.example.demo.Models;

public class OrderItem {
    private Product product;
    private int quantity;
    ////
    private String productName;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    ////
    public OrderItem(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    // Getters and setters
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    ////
    public String getProductName() {
        return product.getProductName();
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}