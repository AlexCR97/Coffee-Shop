package com.example.coffeeshop.backend.entitieslayer;

public class Cart {

    private String userName;
    private String product;
    private int amount;

    public Cart(String userName, String product, int amount) {
        this.userName = userName;
        this.product = product;
        this.amount = amount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userName='" + userName + '\'' +
                ", product='" + product + '\'' +
                ", amount=" + amount +
                '}';
    }
}
