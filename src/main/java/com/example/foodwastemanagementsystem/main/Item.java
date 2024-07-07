package com.example.foodwastemanagementsystem.main;

public interface Item {
    void setName(String name);
    String getName();
    void setPrice(double price);
    double getPrice();
    void setImgSrc(String imgSrc);
    String getImgSrc();
    void setColor(String color);
    String getColor();
}
