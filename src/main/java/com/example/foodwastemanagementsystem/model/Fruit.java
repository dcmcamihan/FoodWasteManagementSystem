package com.example.foodwastemanagementsystem.model;

import com.example.foodwastemanagementsystem.main.Item;

public class Fruit implements Item {
    private String name;
    private double price;
    private String imgSrc;
    private String color;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    @Override
    public String getImgSrc() {
        return imgSrc;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return color;
    }
}
