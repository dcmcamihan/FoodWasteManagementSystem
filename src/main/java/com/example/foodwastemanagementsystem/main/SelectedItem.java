package com.example.foodwastemanagementsystem.main;

import java.util.Objects;

public class SelectedItem {
    private Item item;
    private int quantity;
    private String category;
    private double price;

    public SelectedItem() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemName() {
        return item.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectedItem that = (SelectedItem) o;
        return quantity == that.quantity && Double.compare(price, that.price) == 0 && Objects.equals(item, that.item) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, quantity, category, price);
    }

    @Override
    public String toString() {
        return "SelectedItem{" +
                "item=" + item.getName() +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}

