package com.example.coffeeshop.entitieslayer;

public class Sales {

    private int saleNumber;
    private String userName;
    private String product;
    private float price;
    private int amount;
    private String date;

    public Sales(int saleNumber, String userName, String product, float price, int amount, String date) {
        this.saleNumber = saleNumber;
        this.userName = userName;
        this.product = product;
        this.price = price;
        this.amount = amount;
        this.date = date;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "saleNumber=" + saleNumber +
                ", userName='" + userName + '\'' +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
