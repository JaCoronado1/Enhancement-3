package com.example.finalproject;

public class InventoryItem {
    private int id = -1;  // Default to -1 to signify "not set"
    private String name;
    private int quantity;

    // Constructor without ID (e.g., before inserting into DB)
    public InventoryItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // Constructor with ID (e.g., after fetching from DB)
    public InventoryItem(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters for future flexibility (e.g., if you want to edit items)
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Optional: readable toString method for debugging/logging
    @Override
    public String toString() {
        return "InventoryItem{id=" + id + ", name='" + name + "', quantity=" + quantity + "}";
    }
}



