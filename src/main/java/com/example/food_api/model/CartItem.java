package com.example.food_api.model;

import java.util.List;

public class CartItem {

    private Long id;

    private String itemType;

    private Long itemId;

    private String name;

    private int quantity;

    private String selectedSize;

    private List<Long> selectedDipIds;

    private List<Long> selectedAddOnIds;

    private List<Long> selectedCustomizationIds;

    private List<Long> selectedFruitIds;

    private double unitPrice;

    private double totalPrice;

    public CartItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(String selectedSize) {
        this.selectedSize = selectedSize;
    }

    public List<Long> getSelectedDipIds() {
        return selectedDipIds;
    }

    public void setSelectedDipIds(List<Long> selectedDipIds) {
        this.selectedDipIds = selectedDipIds;
    }

    public List<Long> getSelectedAddOnIds() {
        return selectedAddOnIds;
    }

    public void setSelectedAddOnIds(List<Long> selectedAddOnIds) {
        this.selectedAddOnIds = selectedAddOnIds;
    }

    public List<Long> getSelectedCustomizationIds() {
        return selectedCustomizationIds;
    }

    public void setSelectedCustomizationIds(List<Long> selectedCustomizationIds) {
        this.selectedCustomizationIds = selectedCustomizationIds;
    }

    public List<Long> getSelectedFruitIds() {
        return selectedFruitIds;
    }

    public void setSelectedFruitIds(List<Long> selectedFruitIds) {
        this.selectedFruitIds = selectedFruitIds;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}