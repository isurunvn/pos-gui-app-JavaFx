package com.example.demo.Models;// Models.Order.java

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private int cashierId;
    private String cashierName;
    private List<OrderItem> items;
    private double total;

    public Order(int cashierId) {
        this.cashierId = cashierId;
        this.items = new ArrayList<>();
    }

    public Order(int orderId, int cashierId, double total) {
        this.orderId = orderId;
        this.cashierId = cashierId;
        this.total = total;
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
        calculateTotal();
    }

    private void calculateTotal() {
        total = items.stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();
    }

    // Getters and setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getCashierId() { return cashierId; }

    public void setCashierName(String cashierName) { this.cashierName = cashierName; }
    public String getCashierName() { return cashierName; }

    public List<OrderItem> getItems() { return items; }

    public double getTotal() { return total; }

    public void setTotal(double total) {
        this.total = total;
    }

}