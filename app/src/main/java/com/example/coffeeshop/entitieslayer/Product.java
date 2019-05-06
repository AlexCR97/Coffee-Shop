package com.example.coffeeshop.entitieslayer;

public class Product {

    private String name;
    private String category;
    private float price;
    private String unit;

    public Product(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Product(String name, String category, float price, String unit) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                '}';
    }
}
