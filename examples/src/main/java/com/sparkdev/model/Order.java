package com.sparkdev.model;

import java.time.Instant;

public class Order {
    private Product product;
    private User user;
    private Instant orderInstant;
    private int orderAmount;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getOrderInstant() {
        return orderInstant;
    }

    public void setOrderInstant(Instant orderInstant) {
        this.orderInstant = orderInstant;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "product=" + product +
                ", user=" + user +
                ", orderInstant=" + orderInstant +
                ", orderAmount=" + orderAmount +
                '}';
    }
}
